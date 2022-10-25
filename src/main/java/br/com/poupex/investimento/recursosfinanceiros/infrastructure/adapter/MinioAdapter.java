package br.com.poupex.investimento.recursosfinanceiros.infrastructure.adapter;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.minio.MinioConfig;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import java.io.ByteArrayInputStream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinioAdapter {

  private final MinioConfig config;
  private final MinioClient client;

  public Boolean create(final String object, final byte[] file) {
    try (val inputStream = new ByteArrayInputStream(file)) {
      client.putObject(
        PutObjectArgs.builder().bucket(config.getBucket()).object(config.object(object)).stream(inputStream, inputStream.available(), -1).build()
      );
    } catch (Exception ignored) {
      return false;
    }
    return true;
  }

  public byte[] read(final String object) {
    try (val stream = client.getObject(GetObjectArgs.builder().bucket(config.getBucket()).object(config.object(object)).build())) {
      return stream.readAllBytes();
    } catch (Exception ignored) {
      return null;
    }
  }

  public Boolean delete(final String object) {
    try {
      client.removeObject(RemoveObjectArgs.builder().bucket(config.getBucket()).object(config.object(object)).build());
    } catch (Exception ignored) {
      return false;
    }
    return true;
  }

}
