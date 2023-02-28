package proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import proxy.dto.EtatPartie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MotusProxyImpl implements MotusProxy{

    private HttpClient httpClient = HttpClient.newHttpClient();

    private ObjectMapper objectMapper = new ObjectMapper();
    private final String SERVICE_URL = "http://localhost:8080/motus";
    private final String POST_JOUEUR_ENDPOINT = "/joueur";

    @Override
    public String creerUnCompte(String pseudo) throws PseudoDejaPrisException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVICE_URL+POST_JOUEUR_ENDPOINT+"?pseudo="+pseudo))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()){
                case 201: return response.headers().map().get("token").toString();
                case 409: throw new PseudoDejaPrisException();
                default: return "KO";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String creerUnePartie(String tokenAuthentification) {
        return null;
    }

    @Override
    public EtatPartie proposerMot(String tokenPartie, String proposition) throws MotInexistantException, MaxNbCoupsException, TicketInvalideException {
        return null;
    }

    @Override
    public List<String> getPropositions(String tokenPartie) throws TicketInvalideException, PartieInexistanteException {
        return null;
    }

}
