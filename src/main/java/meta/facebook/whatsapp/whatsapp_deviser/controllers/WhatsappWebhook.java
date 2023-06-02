package meta.facebook.whatsapp.whatsapp_deviser.controllers;

import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.Content;
import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorRequest;
import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorResponse;
import meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp.*;
import meta.facebook.whatsapp.whatsapp_deviser.services.OrchestratorService;
import meta.facebook.whatsapp.whatsapp_deviser.services.WhatsappService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/webhook")
public class WhatsappWebhook {
    private static Logger LOG = LogManager.getLogger(WhatsappWebhook.class);
    //@Value("${whatsapp.token}")
    //public String VERIFY_TOKEN;

    private final RestTemplate restTemplate;
    private final OrchestratorService orchestratorService;
    private final WhatsappService whatsappService;
    private final Map<String, String> whatsappConfig;

    public WhatsappWebhook(RestTemplate restTemplate, OrchestratorService orchestratorService,
                           WhatsappService whatsappService, Map<String, String> whatsappConfig) {
        this.restTemplate = restTemplate;
        this.orchestratorService = orchestratorService;
        this.whatsappService = whatsappService;
        this.whatsappConfig = whatsappConfig;
    }

    @GetMapping
    @CrossOrigin({"*"})
    public ResponseEntity whatsAppHealthCheck(@RequestParam(name="hub.verify.token") String token,
                                              @RequestParam(name="hub.challenge") int challenge) {
        LOG.info("HEALTH CHECK FROM WHATSAPP");
        LOG.info("token: {} " ,token);
        LOG.info("challenge: {} " , challenge);
        if (whatsappConfig.get("verifyToken").equals(token)) {
           LOG.info("token validation success");
           return ResponseEntity.ok(challenge);
        }
        LOG.info("token validation failed");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);


    }

    @PostMapping
    public ResponseEntity webhookController(@RequestBody String body) throws ParseException {
        ThreadContext.put("messageId", UUID.randomUUID().toString());
        LOG.info("messageId", ThreadContext.get("messageId"));
        LOG.info("Body From WhatsApp: {} ", body);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
        JSONObject entry = jsonObject.get("entry") != null ? (JSONObject) ((JSONArray) jsonObject.get("entry")).get(0) : null;
        LOG.info("entry: {} ", entry);
        JSONObject changes = entry.get("changes") != null ? (JSONObject) ((JSONArray) entry.get("changes")).get(0) : null;
        LOG.info("changes: {} ", changes);
        JSONObject changeValue = (JSONObject) changes.get("value");
        LOG.info("changeValue: {} ", changeValue);
        JSONObject metaData = changeValue.get("metaData") != null ? (JSONObject) changeValue.get("metaData") : null;
        LOG.info("metaData: {} ", metaData);
        JSONObject contacts = changeValue.get("contacts") != null ? (JSONObject) ((JSONArray) changeValue.get("contacts")).get(0) : null;
        LOG.info("contacts: {} ", contacts);
        JSONObject profile = contacts.get("profile") != null ? (JSONObject) contacts.get("profile") : null;
        LOG.info("profile: {} ", profile);
        String customerName = profile != null ? (String) profile.get("name") : "";
        LOG.info("customerName: {} ", customerName);
        JSONObject message = changeValue.get("message") != null ? (JSONObject) ((JSONArray) changeValue.get("message")).get(0) : null;
        LOG.info("message: {} ", message);
        String messageBody = "";
        if (entry != null && changes != null && changeValue != null && metaData != null && contacts != null && message != null) {
            String phone_number_id = (String) metaData.get("phone_number_id");
            LOG.info("phone_number_id: {} ", phone_number_id);
            String from = (String) message.get("from");
            LOG.info("from: {} ", from);
            String messageId = (String) message.get("id");
            LOG.info("messageId: {} ", messageId);
            String messageType = (String) message.get("type");
            LOG.info("messageType: {} ", messageType);
            if(messageType.equals("text")){
                JSONObject textObject = (JSONObject) message.get("text");
                messageBody = (String) textObject.get("body");
            }else if (messageType.equals("interactive")){
                JSONObject interactiveObject = (JSONObject) message.get("interactive");
                String interactiveObjectType = (String) interactiveObject.get("type");
                if(interactiveObjectType.equals("list_reply")){
                    messageBody = (String) ((JSONObject) interactiveObject.get("list_reply")).get("description");
                }else if(interactiveObjectType.equals("button_reply")){
                    messageBody = (String) ((JSONObject) interactiveObject.get("button_reply")).get("title");
                }
            }
            LOG.info("messageBody: {} ", messageBody);
            Data data = new Data();
              if(messageBody != null) {
                  OrchestratorRequest orchestratorRequest = orchestratorService.createOrchestratorRequest(messageBody, from, customerName);
                  OrchestratorResponse orchestratorResponse = orchestratorService.sendTogetOrchestratoreResponse(orchestratorRequest);
                LOG.info("orchestrator Response: {} ", orchestratorResponse.toString());
                  try {
                      data = this.whatsappService.createWhatsappResponseData(orchestratorResponse, from);
                  } catch (Exception e) {

                        LOG.error("Error in creating whatsapp response data: {} ", e.getMessage());
                        data = null; //put error message
                  }
                  whatsappService.sendToWhatsapp(phone_number_id, data);

              }
            return new ResponseEntity(data, HttpStatus.OK);
        }
        else {
            LOG.error("Error in parsing the body");
            LOG.info("messageBody:{}", messageBody);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
