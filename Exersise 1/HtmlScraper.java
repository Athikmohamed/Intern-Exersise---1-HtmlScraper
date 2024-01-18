import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONObject;
import org.json.JSONArray;

public class HtmlScraper {
    public static void main(String[] args) {
        try {
            String html = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <title>example.com</title>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <meta name=\"description\" content=\"\">\n" +
                    "    <meta name=\"author\" content=\"\">\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div id='main'>\n" +
                    "      <ul>\n" +
                    "        <li>\n" +
                    "          <strong class='capital'>Lansing</strong>\n" +
                    "          <span class='state'>Michigan  </span>\n" +
                    "        </li>\n" +
                    "        <li>\n" +
                    "          <strong class='capital'>Sacramento</strong>\n" +
                    "          <span class='state'>       California</span>\n" +
                    "        </li>\n" +
                    "      </ul>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";

            // Parse the HTML
            Document document = Jsoup.parse(html);

            // Extract data and store it in a JSON object
            JSONObject data = new JSONObject();
            JSONArray capitalsArray = new JSONArray();

            Elements liElements = document.select("li");
            for (Element liElement : liElements) {
                String capital = liElement.select("strong.capital").text().trim();
                String state = liElement.select("span.state").text().trim();

                JSONObject capitalObject = new JSONObject();
                capitalObject.put("capital", capital);
                capitalObject.put("state", state);

                capitalsArray.put(capitalObject);
            }

            // Add the capitals array to the data object
            data.put("capitals", capitalsArray);

            // Add the summary object to the data object
            JSONObject summaryObject = new JSONObject();
            summaryObject.put("numberOfCapitals", capitalsArray.length());
            data.put("summary", summaryObject);

            // Print the JSON object
            System.out.println(data.toString());

        } catch (Exception e) {
            // Handle the JSON exception
            e.printStackTrace();
        }
    }
}
