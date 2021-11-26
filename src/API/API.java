package API;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private final String API_KEY = "AIzaSyB0IG79cXoG_hZoGtrH3oOfLyyHFJhMWfE";
    private Double[][] distances;
    private String[] cities = {"Akiruno,+Tokyo,+Japan","Akishima,+Tokyo,+Japan","Chofu,+Tokyo,+Japan","Fuchu,+Tokyo,+Japan","Fussa,+Tokyo,+Japan","Hachioji,+Tokyo,+Japan","Hamura,+Tokyo,+Japan","Higashikurume+City,+Tokyo","Higashimurayama+City,+Tokyo","Higashiyamato+City,+Tokyo","Hino+City,+Tokyo","Inagi+City,+Tokyo","Kiyose+City,+Tokyo","Kodaira+City,+Tokyo","Koganei+City,+Tokyo","Kokubunji+City,+Tokyo","Komae+City,+Tokyo","Kunitachi+City,+Tokyo","Machida+City,+Tokyo","Mitaka+City,+Tokyo","Musashimurayama+City,+Tokyo","Musashino+City,+Tokyo","Nishitokyo+City,+Tokyo","Ome+City,+Tokyo","Tachikawa+City,+Tokyo","Tama+City,+Tokyo"};
    private final int n = cities.length;

    //downloading the data
    public String getData(String source, String destination) throws Exception {
        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&key=" + API_KEY;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        //System.out.println(response);
        return response;
    }

    public void parse(String response,int i,int j){
        long distance = -1L;
        //parsing json data and updating data
        {
            try {
                JSONParser jp = new JSONParser();
                JSONObject jo = (JSONObject) jp.parse(response);
                JSONArray ja = (JSONArray) jo.get("rows");
                jo = (JSONObject) ja.get(0);
                ja = (JSONArray) jo.get("elements");
                jo = (JSONObject) ja.get(0);
                JSONObject je = (JSONObject) jo.get("distance");
                distance = (long) je.get("value");

                distances[i][j] = Double.valueOf(distance / (200/9));

            } catch (Exception e) {
                System.out.println(e + " for " + cities[j]);
            }
        }
    }

    public void make_text_file() throws FileNotFoundException {
        PrintWriter out =new PrintWriter("distanceMatrix.txt");
        for (int i = 1 ; i < n ; i++) {
            for (int j = 1; j < n; j++) {
                out.print(distances[i][j] + " ");
            }
            out.println();
        }
        out.close();
    }

    public Double[][] callApi() throws Exception{
        distances = new Double[n][n];
        int count=0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
//                System.out.print(++count+"/100 ");
                if (i != j) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else {
                    distances[i][j] = 0.0;
                }
            }
        make_text_file();

        return distances;
    }
}