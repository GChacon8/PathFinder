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
    private String[] cities = {"Akiruno,+Tokyo,+Japan","Akishima,+Tokyo,+Japan","Chofu,+Tokyo,+Japan","Fuchu,+Tokyo,+Japan",
            "Fussa,+Tokyo,+Japan","Hachioji,+Tokyo,+Japan","Hamura,+Tokyo,+Japan","Higashikurume+City,+Tokyo","Higashimurayama+City,+Tokyo",
            "Higashiyamato+City,+Tokyo","Hino+City,+Tokyo","Inagi+City,+Tokyo","Kiyose+City,+Tokyo","Kodaira+City,+Tokyo","Koganei+City,+Tokyo",
            "Kokubunji+City,+Tokyo","Komae+City,+Tokyo","Kunitachi+City,+Tokyo","Machida+City,+Tokyo","Mitaka+City,+Tokyo","Musashimurayama+City,+Tokyo",
            "Musashino+City,+Tokyo","Nishitokyo+City,+Tokyo","Ome+City,+Tokyo","Tachikawa+City,+Tokyo","Tama+City,+Tokyo"};
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

                distances[i][j] = Double.valueOf((distance / (200/9))/60);

            } catch (Exception e) {
                System.out.println(e + " for " + cities[j]);
            }
        }
    }

    public void make_text_file() throws FileNotFoundException {
        PrintWriter out =new PrintWriter("TimeMatrix.txt");
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0; j < n; j++) {
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
                if (i == 0 && (j==23 || j == 6 || j==4 || j ==5)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 1 && (j==4 || j == 10 || j==5 || j ==24)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 2 && (j==16 || j == 19 || j==14 || j ==3 || j==11)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 3 && (j==11 || j == 25 || j==10 || j ==17 || j==15 || j==14 || j==2)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 4 && (j==5 || j == 0 || j==6 || j ==20 || j==24 || j==1)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 5 && (j==18 || j == 25 || j==10 || j ==1 || j==4 || j==0)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 6 && (j==0 || j == 4 || j==23)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 7 && (j==22 || j == 13 || j==8 || j ==12)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 8 && (j==9 || j == 13 || j==7 || j ==12)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 9 && (j==8 || j == 13 || j==24 || j ==21)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 10 && (j==25 || j == 5 || j==1 || j ==24 || j==17 || j==3)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 11 && (j==25 || j == 3 || j==2)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 12 && (j==7 || j == 8)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 13 && (j==8 || j == 7 || j==22 || j ==14 || j==15 || j==24 || j==9)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 14 && (j==3 || j == 15 || j==13 || j ==22 || j==21 || j==19 || j==2)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 15 && (j==3 || j == 17 || j==24 || j ==13 || j==14)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 16 && j==2 ) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 17 && (j==3 || j == 10 || j==24 || j ==15)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 18 && (j==5 || j == 25)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 19 && (j==2 || j == 14 || j==21)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 20 && (j==4 || j == 24 || j==9)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 21 && (j==22 || j == 14 || j==19)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 22 && (j==7 || j == 13 || j==14 || j ==21)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 23 && (j==6 || j == 0)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 24 && (j==10 || j == 1 || j==4 || j ==20 || j==9 || j==13 || j==15 || j==17)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i == 25 && (j==18 || j == 5 || j==10 || j ==3 || j==11)) {
                    String response = getData(cities[i], cities[j]);
                    parse(response, i, j);
                }
                else if (i==j)    {
                    distances[i][j] = 0.0;
                }
                else{
                    distances[i][j] = 99999999999.0;
                }
            }
        make_text_file();
        return distances;
    }
}