/* Name: Mohammad Shahnaei
 * Student ID: 220907952
 * 
 * Important note: the lines of code for which their comment has an *Update* notion are what's been changed by me.
 */




package ca.yorku.cmg.lob.stockexchange.tradingagent;


import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/**
 * An trading agent that receives news and reacts by submitting ask or bid orders.
 */
public abstract class TradingAgent implements INewsObserver{
	protected ITradingStrategy strat; // Update: for referencing to the Strategy class, we make a Strategy reference attribute.
	protected Trader t;
	protected StockExchange exc;
	protected NewsBoard news;
	
	
	/**
	 * Update: Constructor
	 * @param t The {@linkplain Trader} object associated with the agent.
	 * @param e The {@linkplain StockExchange} object at which the agent has an account and trades in. 
	 * @param n The {@linkplain NewsBoard} object that generates news events.
	 * @param s The {@linkplain ITradingStrategy} object that now defines the strategy
	 */
	public TradingAgent(Trader t, StockExchange e, NewsBoard n, ITradingStrategy s) {
		this.t=t;
		this.exc = e;
		this.news = n;
		this.strat = s;
		this.news.registerObserver(this);
	}
	/* 
	 * Receives event updates from NewsBoard
	 * @param e The event occurance
	 * 
	 * 
	 */
	
	@Override
	
	public void update(Event e) {
		examineEvent(e);
	}
	

	
	/**
	 * Method to be called as time advances to {@code time}. In response the TradingAgent will poll the NewsBoard for events.
	 * @param time The time to advance to.
	 */
	public void timeAdvancedTo(long time) {
		pollForEvents(time);
	}

	/** Update:
	 * Examine if an event is relevant for the Agent, i.e., if the Agent has a position on it.
	 * @param e The {@linkplain Event} object in question
	 * 
	 * Update_1: now i have the strategy class here which delegates trade processing to the strategy pattern.
	 * Update_2: Fixed the typo Secrity to Security.
	 */
	private void examineEvent(Event e) {
		int positionInSecurity = exc.getAccounts().getTraderAccount(t).getPosition(e.getSecurity().getTicker());
		if (positionInSecurity > 0) {
			strat.actOnEvent(e, positionInSecurity, exc.getPrice(e.getSecurity().getTicker()));

		}
	}

	
	/**
	 * Check into the {@linkplain NewsBoard} if there are any events at time {@code time}. If there is one (it assumes only one event at a time), send it for examination.
	 * @param time The time for which to poll for events. Unit is days.
	 */
	private void pollForEvents(long time) {
		Event e = news.getEventAt(time);
		if (e!=null) {
			examineEvent(e);
		}

	}
	
	/*
	 * this setter, sets our strategy.
	 */
	
	
	
	public void setStrategy(ITradingStrategy strategy) {
		this.strat = strategy;
	}
	
	
	
	
	/**
	 * Update:
	 * Act in response to a news {@linkplain Event}. Exact reaction strategy to be implemented by specialized agents.
	 * @param e The {@linkplain Event} in question
	 * @param pos The position (number of units) of the trader to the ticker that is mentioned in the Event.
	 * @param price The current price of the relevant ticker. 
	 * Update_1: Trading now is done through the strategy class so it's the strategy class's responsibility to call for action here.
	 * Update_2: changed the abstract to normal, because previously we had this method implemented in the conservative and aggressive classes, but now strategy class takes the responsibility so no need for abstraction here.
	 * Update_3: removed this method due the fact that its not abstract anymore, moreover, we already have this method in the ExamineEvent action method.
	 */
	/*	protected void actOnEvent(Event e, int pos, int price) {
	 *		strat.actOnEvent(t, exc, news, e , pos, price);
	 */		
	
}
	
	
	

