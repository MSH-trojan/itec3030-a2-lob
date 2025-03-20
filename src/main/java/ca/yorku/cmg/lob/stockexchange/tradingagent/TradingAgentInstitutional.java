package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/*
 * this is the class for our Institutional Agent/Agents
 * 
 */

public class TradingAgentInstitutional extends TradingAgent{
	public TradingAgentInstitutional(Trader t, StockExchange e, NewsBoard n, ITradingStrategy s) {
		super(t, e, n, s);
	}
}