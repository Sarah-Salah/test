package meta.facebook.whatsapp.whatsapp_deviser.services;

import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.Content;
import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorRequest;
import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorResponse;
import meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrchestratorServiceImpl implements OrchestratorService{

    private final RestTemplate restTemplate;
    private final Map<String, String> whatsappConfig;

    public OrchestratorServiceImpl(RestTemplate restTemplate, Map<String, String> whatsappConfig) {
        this.restTemplate = restTemplate;
        this.whatsappConfig = whatsappConfig;
    }


    @Override
    public Data createWhatsappResponseData() {
        return null;
    }

    @Override
    public OrchestratorRequest createOrchestratorRequest(String messageBody, String userDial, String customerName) {
        OrchestratorRequest orchestratorRequest = new OrchestratorRequest();
        Content content = new Content();
        content.setUserInput(messageBody);
        content.setUserDial(userDial);
        content.setChannel(whatsappConfig.get("whatsappChannel"));
        content.setSourceChannel(whatsappConfig.get("whatsappSourceChannel"));
        content.setClientName(customerName);
        orchestratorRequest.setContent(content);
        return orchestratorRequest;
    }

    @Override
    public OrchestratorResponse sendTogetOrchestratoreResponse(OrchestratorRequest orchestratorRequest) {
        return restTemplate.postForObject(whatsappConfig.get("orchestratorUrl"), orchestratorRequest, OrchestratorResponse.class);
    }



}
