import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class SkyscannerAPITesting {

@DataProvider(name = "data")
public Object[] createData(){
        return new String[][]{
                {"UK", "EUR", "en-US", "Frankfurt", "London", "FRAN-sky", "LOND-sky", "€", "Places[0].CityName", "Places[1].CityName"},
                {"US", "USD", "en-US", "Miami", "New York", "MIA-sky", "NYCA-sky", "€", "Places[2].CityName", "Places[0].CityName"},
                {"JP", "JPY", "en-US", "Tokyo", "Beijing", "TYOA-sky", "BJSA-sky", "€", "Places[0].CityName", "Places[2].CityName"}

        };
}
@BeforeTest
public void TestSetup(){
        RestAssured.baseURI="https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

        }
@Test(dataProvider = "data")
public void TestCase01(String market, String currency, String locale, String placeOne, String placeTwo, String placeidOne, String placeidTwo, String symbol, String jspathOne, String jspathTwo ){

        Response responseOne = given().
                header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com").
                header("x-rapidapi-key", "a659416d69mshb0d9eaba28daa28p1ea282jsn96bf7025223c").
                pathParam("market", market).
                pathParam("currency", currency).
                pathParam("locale", locale).
                param("query", placeOne).
                when().
                get("/apiservices/autosuggest/v1.0/{market}/{currency}/{locale}/").
                then().assertThat().
                statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("Places[0].PlaceId", equalTo(placeidOne)).
                extract().response();

        String responseOneString = responseOne.asString();
        JsonPath jsResponseOne = new JsonPath(responseOneString);
        String responseplaceidOne = jsResponseOne.get("Places[0].PlaceId");
        System.out.println(responseplaceidOne);

        Response responseTwo = given().
                header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com").
                header("x-rapidapi-key", "a659416d69mshb0d9eaba28daa28p1ea282jsn96bf7025223c").
                pathParam("market", market).
                pathParam("currency", currency).
                pathParam("locale", locale).
                param("query", placeTwo).
                when().
                get("/apiservices/autosuggest/v1.0/{market}/{currency}/{locale}/").
                then().assertThat().
                statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("Places[0].PlaceId", equalTo(placeidTwo)).
                extract().response();

        String responseStringTwo = responseTwo.asString();
        JsonPath jsResponseTwo = new JsonPath(responseStringTwo);
        String responseplaceidTwo = jsResponseTwo.get("Places[0].PlaceId");
        System.out.println(responseplaceidTwo);

      given().
                header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com").
                header("x-rapidapi-key", "a659416d69mshb0d9eaba28daa28p1ea282jsn96bf7025223c").
                pathParam("market", "UK").
                pathParam("currency", "EUR").
                pathParam("locale", "en-US").
                pathParam("originplace", responseplaceidOne).
                pathParam("destinationplace", responseplaceidTwo).
                pathParam("outbounddate", "anytime").
                param("inboundpartialdate", "anytime").
                when().
                get("/apiservices/browsequotes/v1.0/{market}/{currency}/{locale}/{originplace}/{destinationplace}/{outbounddate}").
                then().assertThat().
                statusCode(200).
                and().contentType(ContentType.JSON).
                and().body(jspathOne, equalTo(placeOne)).
                and().body(jspathTwo, equalTo(placeTwo)).
                and().body("Quotes[0].QuoteId", equalTo(1)).
                and().body("Currencies.Symbol", hasItem(symbol)).extract().response();

    }
}
