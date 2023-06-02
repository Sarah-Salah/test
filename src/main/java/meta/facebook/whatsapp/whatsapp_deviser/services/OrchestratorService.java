package meta.facebook.whatsapp.whatsapp_deviser.services;

import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorRequest;
import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorResponse;
import meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp.Data;


public interface  OrchestratorService {
    Data createWhatsappResponseData();

    OrchestratorRequest createOrchestratorRequest(String messageBody, String userDial, String customerName);
    OrchestratorResponse sendTogetOrchestratoreResponse (OrchestratorRequest request);
}
