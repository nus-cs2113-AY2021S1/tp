package seedu.duke.api;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import seedu.duke.data.exception.DukeException;

import java.util.List;

public class StockPriceFetcher {
    private String apiKey;
    private int timeout = 3000;

    public StockPriceFetcher() {
        apiKey = "O8EVQ7YSWDW08BN9";
        timeout = 3000;
    }

    public double fetchLatestPrice(String symbol) throws DukeException {
        StockData stockData = fetchLatestStockData(symbol);

        return (stockData.getHigh() + stockData.getLow()) / 2;
    }

    public StockData fetchLatestStockData(String symbol) throws DukeException {
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
        TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

        try {
            IntraDay response = stockTimeSeries.intraDay(symbol, Interval.ONE_MIN, OutputSize.COMPACT);
            List<StockData> stockData = response.getStockData();

            return stockData.get(0);
        } catch (AlphaVantageException e) {
            throw new DukeException("Failed to retrieve price! Please try again later.");
        }
    }

}
