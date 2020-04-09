package com.jarolift.domotic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ButtonsControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void returnErrorIfChannelIsNotValid() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/button/stop/channel/3");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void returnErrorIfButtonIsNotValid() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/button/fake/channel/1");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void pushStopButtonChannel1() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/button/stop/channel/1");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("{\"button\":\"stop\",\"channel\":1}");
    }

    @Test
    public void pushUpButtonChannel1() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/button/up/channel/1");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("{\"button\":\"up\",\"channel\":1}");
    }

    @Test
    public void pushDownButtonChannel1() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/button/down/channel/1");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("{\"button\":\"down\",\"channel\":1}");
    }

    @Test
    public void pushMiddleButtonChannel1() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/middle/channel/1");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("{\"button\":\"middle\",\"channel\":1}");
    }

    @Test
    public void pushStopButtonForAllChannels() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/button/stop/channel/0");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody()).isEqualTo("{\"button\":\"stop\",\"channel\":0}");
    }
}
