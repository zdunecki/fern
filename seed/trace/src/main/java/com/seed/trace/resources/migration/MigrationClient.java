package com.seed.trace.resources.migration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.seed.trace.core.ApiError;
import com.seed.trace.core.ClientOptions;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.core.RequestOptions;
import com.seed.trace.resources.migration.requests.GetAttemptedMigrationsRequest;
import com.seed.trace.resources.migration.types.Migration;
import java.io.IOException;
import java.util.List;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MigrationClient {
    protected final ClientOptions clientOptions;

    public MigrationClient(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
    }

    public List<Migration> getAttemptedMigrations(GetAttemptedMigrationsRequest request) {
        return getAttemptedMigrations(request, null);
    }

    public List<Migration> getAttemptedMigrations(
            GetAttemptedMigrationsRequest request, RequestOptions requestOptions) {
        HttpUrl _httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("migration-info")
                .addPathSegments("all")
                .build();
        RequestBody _requestBody = null;
        Request.Builder _requestBuilder = new Request.Builder()
                .url(_httpUrl)
                .method("GET", _requestBody)
                .headers(Headers.of(clientOptions.headers(requestOptions)))
                .addHeader("Content-Type", "application/json");
        _requestBuilder.addHeader("admin-key-header", request.getAdminKeyHeader());
        Request _request = _requestBuilder.build();
        try {
            Response _response = clientOptions.httpClient().newCall(_request).execute();
            if (_response.isSuccessful()) {
                return ObjectMappers.JSON_MAPPER.readValue(
                        _response.body().string(), new TypeReference<List<Migration>>() {});
            }
            throw new ApiError(
                    _response.code(),
                    ObjectMappers.JSON_MAPPER.readValue(_response.body().string(), Object.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
