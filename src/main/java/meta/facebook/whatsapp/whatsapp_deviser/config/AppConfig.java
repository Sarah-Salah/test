package meta.facebook.whatsapp.whatsapp_deviser.config;


import meta.facebook.whatsapp.whatsapp_deviser.models.app.GeneralConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;


import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    Map<String, String > whatsappConfig() {
        GeneralConfig generalConfig1 = new GeneralConfig();
        generalConfig1.setName("whatsappChannel");
        generalConfig1.setValue("WhatsappBusiness");
        GeneralConfig generalConfig2 = new GeneralConfig();
        generalConfig2.setName("whatsappSourceChannel");
        generalConfig2.setValue("WhatsappMeta");
        GeneralConfig generalConfig3 = new GeneralConfig();
        generalConfig3.setName("verifyToken");
        generalConfig3.setValue("WHATSAPPToken");
        GeneralConfig generalConfig4 = new GeneralConfig();
        generalConfig4.setName("whatsAppUrl");
        generalConfig4.setValue("");
        GeneralConfig generalConfig5 = new GeneralConfig();
        generalConfig5.setName("whatsAppToken");
        generalConfig5.setValue("");
        GeneralConfig generalConfig6 = new GeneralConfig();
        generalConfig6.setName("OrchestratorURL");
        generalConfig6.setValue("");

        List<GeneralConfig> generalConfigList = new ArrayList<>();
        generalConfigList.add(generalConfig1);
        generalConfigList.add(generalConfig2);
        generalConfigList.add(generalConfig3);
        generalConfigList.add(generalConfig4);
        generalConfigList.add(generalConfig5);
        generalConfigList.add(generalConfig6);
        Map<String, String> map = new HashMap<>();
        generalConfigList.stream().forEach(generalConfig -> map.put(generalConfig.getName(), generalConfig.getValue()));
        return map;
    }
}
