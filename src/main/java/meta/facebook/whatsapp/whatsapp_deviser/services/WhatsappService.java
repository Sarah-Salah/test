package meta.facebook.whatsapp.whatsapp_deviser.services;

import meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator.OrchestratorResponse;
import meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp.Data;

public interface WhatsappService {
    Data createWhatsappResponseData(OrchestratorResponse orchestratorResponse, String userDial) throws Exception;

    void sendToWhatsapp(String phoneNumberId, Data data);

}
