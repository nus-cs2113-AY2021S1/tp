package seedu.duke.api;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import seedu.duke.exception.PaperTradeException;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockPriceFetcher {
    private static Logger logger = Logger.getLogger("tp");
    private String[] apiKeys = {"O8EVQ7YSWDW08BN9", "3FPNCQ0JNYEE8BTK", "072ESZXDIDEATZA2", "Q3JB9M20HYCUXBFR",
            "K0O2GKDS6Q14H72Q", "9N7N2SEJ1XB2MYXH", "MPGB17OYPB9T9UV4", "V1E553AD7IPL60L7",
            "XLCF6EOMHCE76FMG", "SXRMWI9LMKTUGMYR", "UDK2VLO64K1Z8UA2", "8X57YW7EXHPZ66J1"};
    private int timeout = 3000;

    public StockPriceFetcher() {
        timeout = 3000;
    }

    public double fetchLatestPrice(String symbol) throws PaperTradeException {
        logger.log(Level.INFO, "fetching latest stock data for " + symbol);
        StockData stockData = fetchLatestStockData(symbol);

        return (stockData.getHigh() + stockData.getLow()) / 2;
    }

    public StockData fetchLatestStockData(String symbol) throws PaperTradeException {
        Random random = new Random();
        String randomApiKey = apiKeys[random.nextInt(apiKeys.length)];
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(randomApiKey, timeout);
        TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

        try {
            IntraDay response = stockTimeSeries.intraDay(symbol, Interval.ONE_MIN, OutputSize.COMPACT);
            List<StockData> stockData = response.getStockData();

            return stockData.get(0);
        } catch (AlphaVantageException e) {
            logger.log(Level.INFO, "failed to fetch price from API");
            throw new PaperTradeException("API limit has reached. Take a chill pill and test again a moment later :)");
        }
    }

}
