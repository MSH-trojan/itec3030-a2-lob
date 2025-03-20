package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;


/* Update:
 * this is the new concrete class for our Agent creation in the first hand.
 */




public class TradingAgentFactory extends AbstractTradingAgentFactory{

	
	
	
	@Override
	public TradingAgent createAgent(String type, String style, Trader t, StockExchange e, NewsBoard n) {
		ITradingStrategy strategy;
		
		if (style.equalsIgnoreCase("Aggressive")) {
			strategy = new TradingStrategyAggressive(t, e);
			
		}
		else if (style.equalsIgnoreCase("Conservative")) {
			strategy = new TradingStrategyConservative(t, e);
			
		}
		else {
			throw new IllegalArgumentException("Invalid trading style: "+ style);
		}
		
		if (type.equalsIgnoreCase("Institutional")) {
			return new TradingAgentInstitutional(t, e, n, strategy);
			
		}
		
		else if (type.equalsIgnoreCase("Retail")){
				return new TradingAgentRetail(t, e, n, strategy);
	}
		else {
			throw new IllegalArgumentException("Invalid trading Agent type: " + type);
		}
}
}