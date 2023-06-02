package meta.facebook.whatsapp.whatsapp_deviser.services;

import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorResponse;
import meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class WhatsappServiceImpl implements WhatsappService{

    private static Logger LOG = LogManager.getLogger(WhatsappServiceImpl.class);
    private final RestTemplate restTemplate;
    private final Map<String, String> whatsappConfig;


    public WhatsappServiceImpl(RestTemplate restTemplate, Map<String, String> whatsappConfig) {
        this.restTemplate = restTemplate;
        this.whatsappConfig = whatsappConfig;

    }

    @Override
    public Data createWhatsappResponseData(OrchestratorResponse orchestratorResponse, String userDial) throws Exception {
        Data data = new Data();
            if(orchestratorResponse.getMessage()!=null){
                data.setMessagingProduct("whatsapp");
                data.setTo(userDial);
                data.setRecipientType("individual");
                //in case of Quiq reply
                if(orchestratorResponse.getComponents().size()>0){
                    data.setType("interactive");
                    Interactive interactive = new Interactive();
                    interactive.setType("button");
                    Body interactiveBody = new Body();
                    interactiveBody.setText(orchestratorResponse.getMessage());
                    if(orchestratorResponse.getImageUrl()!=null){
                        Header header = new Header();
                        Image image = new Image();
                        image.setLink(orchestratorResponse.getImageUrl());
                        header.setImage(image);
                        header.setType("image");
                        interactive.setHeader(header);
                    }
                    interactive.setBody(interactiveBody);
                    //here actions
                    Action action = new Action();
                    //set buttons
                    List<Button> buttons = new ArrayList<>();
                    orchestratorResponse.getComponents().forEach(component -> {
                        Button button = new Button();
                        button.setType("reply");
                        Reply reply = new Reply();
                        reply.setId(component);
                        reply.setTitle(component);
                        button.setReply(reply);
                        buttons.add(button);
                    });
                    action.setButtons(buttons);
                    interactive.setAction(action);
                    data.setInteractive(interactive);
                    LOG.info("interactive data: {}", data.toString());
                    //in case of list
                }else if (orchestratorResponse.getInteractiveList().size()>0){
                    data.setType("interactive");
                    Interactive interactive = new Interactive();
                    interactive.setType("list");
                    Body body = new Body();
                    body.setText(orchestratorResponse.getMessage());
                    interactive.setBody(body);
                    Body interactiveBody = new Body();
                    interactiveBody.setText(orchestratorResponse.getMessage());
                    Action action = new Action();
                    action.setButton("list");
                    Section section = new Section();

                    List<Row> rows = new ArrayList<>();
                    orchestratorResponse.getInteractiveList().stream().forEach(interactiveList -> {
                        Row row = new Row();
                        row.setId(interactiveList.getHeader()); //check
                        row.setTitle(interactiveList.getHeader());
                        row.setDescription(interactiveList.getDescription()!=null?interactiveList.getDescription():"");
                        rows.add(row);
                    });
                    section.setRows(rows);
                    List<Section> sectionList = new ArrayList<>();
                    sectionList.add(section);
                    action.setSections(sectionList);
                    interactive.setAction(action);
                    data.setInteractive(interactive);
                } else {
                    data.setType("text");
                    Text text = new Text();
                    text.setBody(orchestratorResponse.getMessage());
                    data.setText(text);
                }
                LOG.info("DATA OUT::: {}", data.toString());
            }else{
                LOG.info("DATA OUT::: {}", data.toString());
                throw new Exception("No message found");
            }
        return data;
    }
    @Override
    public void sendToWhatsapp(String phoneNumberId, Data data) {
        Iterator iterator = whatsappConfig.entrySet().iterator();
        while (iterator.hasNext()){
            LOG.info("whatsappConfig: {}", iterator.next().toString());
        }
        String whatsappUrl = whatsappConfig.get("whatsappUrl")
                .replace("phoneNumberId", phoneNumberId)
                .replace("whatsappToken", whatsappConfig.get("whatsappToken"));
        LOG.info("whatsappUrl: {}", whatsappUrl);
        this.restTemplate.postForObject(whatsappUrl, data, Object.class);
    }

}
