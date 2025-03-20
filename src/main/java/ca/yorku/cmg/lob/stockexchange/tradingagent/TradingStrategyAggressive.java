package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;

/**
 * A trading agent kind that reacts more eagerly to news.
 */


	/*	Update_1: implementing the interface
	 *  Update_2: Changed the name of the class from "TradingAgentAggressive" to be "TradingStrategyAggressive"  
	 * 
	 * 
	 */


public class TradingStrategyAggressive implements ITradingStrategy { 
	private Trader t;
	private StockExchange exc;
	
	
	 public TradingStrategyAggressive(Trader t, StockExchange exc) {
	        this.t = t;
	        this.exc = exc;
	        
	    }
	
	@Override
	public void actOnEvent(Event e, int pos, int price) {

		IOrder newOrder = null;
		
		if (e instanceof GoodNews) {
            newOrder = new Bid(t,e.getSecurity(),(int) Math.round(price*1.05), (int) Math.round(pos*0.5),e.getTime());
        } else if (e instanceof BadNews) {
        	newOrder = new Ask(t,e.getSecurity(),(int) Math.round(price*0.90), (int) Math.round(pos*0.8),e.getTime());
        } else {
            System.out.println("Unknown event type");
        }
		
		if (newOrder!=null) {
			exc.submitOrder(newOrder,e.getTime());
		}
    }


}
