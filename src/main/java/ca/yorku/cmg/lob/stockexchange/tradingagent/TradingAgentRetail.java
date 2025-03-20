package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/*
 * this is the class for our Retail Agent/Agents
 * 
 */

public class TradingAgentRetail extends TradingAgent{
	public TradingAgentRetail(Trader t, StockExchange e, NewsBoard n, ITradingStrategy s) {
		super(t, e, n, s);
	}
}