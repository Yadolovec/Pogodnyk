package PetP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private TextField cityField;

    @FXML
    private Button getData;

    @FXML
    private Text max_temp;

    @FXML
    private Text min_temp;

    @FXML
    private Text preasure;

    @FXML
    private Text temp_feel;

    @FXML
    private Text temp_info;

    @FXML
    void initialize(){
        getData.setOnAction(e -> {
            String userCity = cityField.getText().trim();
            if (!userCity.isEmpty()){
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q="+userCity+"&appid=61cfe3fb7f3a97c8b49e6dfa46f45339&units=metric");
                
                if(!output.isEmpty()){
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("ТЕМПЕРАТУРА "+obj.getJSONObject("main").getDouble("temp"));
                    temp_feel.setText("ВІДЧУВАЄТЬСЯ "+obj.getJSONObject("main").getDouble("feels_like"));
                    max_temp.setText("МАКСИМУМ "+obj.getJSONObject("main").getDouble("temp_max"));
                    min_temp.setText("МІНІМУМ "+obj.getJSONObject("main").getDouble("temp_min"));
                    preasure.setText("ТИСК "+obj.getJSONObject("main").getDouble("pressure"));
                }
            }    
        });
    }

    /**
     * @param urlAdress
     * @return
     */
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            
            while ((line = bufferedReader.readLine()) != null){
                content.append(line+"\n");
            }
            bufferedReader.close();
        
        } catch (Exception e) {
            System.out.println("Місто не було знайдено");
        }
        return content.toString();

    }

}
