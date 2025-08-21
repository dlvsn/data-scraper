package denys.mazurenko.service.api;

import static denys.mazurenko.config.Constants.ACCEPT_HEADER;
import static denys.mazurenko.config.Constants.ACCEPT_LANGUAGE;
import static denys.mazurenko.config.Constants.CONTENT_TYPE;
import static denys.mazurenko.config.Constants.ORIGIN;
import static denys.mazurenko.config.Constants.REFER;
import static denys.mazurenko.config.Constants.SEC_CH_UA;
import static denys.mazurenko.config.Constants.SEC_CH_UA_MOBILE;
import static denys.mazurenko.config.Constants.SEC_CH_UA_PLATFORM;
import static denys.mazurenko.config.Constants.SEC_FETCH_DEST;
import static denys.mazurenko.config.Constants.SEC_FETCH_MODE;
import static denys.mazurenko.config.Constants.SEC_FETCH_SITE;
import static denys.mazurenko.config.Constants.USER_AGENT;
import static denys.mazurenko.config.Constants.USER_AGENT_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import denys.mazurenko.dto.external.jobfunctions.JobFunctionsExternalResponseDto;
import denys.mazurenko.exception.ApiRequestException;
import denys.mazurenko.exception.MappingFailedException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class JobFunctionsApiService implements JobFunctionsApiDataFetcher {
    private final String apiUrl;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public JobFunctionsApiService(@Value("${website.url.api.job.functions}")
                                  String apiUrl,
                                  OkHttpClient okHttpClient,
                                  ObjectMapper objectMapper) {
        this.apiUrl = apiUrl;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public JobFunctionsExternalResponseDto fetchData() {
        try(Response response = okHttpClient.newCall(initRequest()).execute()) {
            if (!response.isSuccessful()) {
                throw new ApiRequestException(
                        String.format("Can't fetch Job Functions: HTTP %d %s",
                        response.code(),
                        response.message()));
            }
            try {
                return objectMapper.readValue(response.body().string(), JobFunctionsExternalResponseDto.class);
            } catch (IOException e) {
                throw new MappingFailedException(
                        String.format("Failed to map Job Functions response. %s",
                                e.getMessage()));
            }
        } catch (IOException e) {
            throw new ApiRequestException(
                    String.format("Request failed: %s", e.getMessage()));
        }
    }

    private Request initRequest() {
        return new Request.Builder()
                .url(apiUrl)
                .addHeader(ACCEPT_HEADER, "application/json")
                .addHeader(CONTENT_TYPE, "application/json")
                .addHeader(ACCEPT_LANGUAGE, "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .addHeader(ORIGIN, "https://jobs.techstars.com")
                .addHeader(REFER, "https://jobs.techstars.com/")
                .addHeader(SEC_CH_UA, "\"Not;A=Brand\";v=\"99\", \"Google Chrome\";v=\"139\", \"Chromium\";v=\"139\"")
                .addHeader(SEC_CH_UA_MOBILE, "?0")
                .addHeader(SEC_CH_UA_PLATFORM, "\"macOS\"")
                .addHeader(SEC_FETCH_DEST, "empty")
                .addHeader(SEC_FETCH_MODE, "cors")
                .addHeader(SEC_FETCH_SITE, "cross-site")
                .addHeader(USER_AGENT, USER_AGENT_VALUE)
                .build();
    }
}
