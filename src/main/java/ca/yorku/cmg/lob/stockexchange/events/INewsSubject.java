package ca.yorku.cmg.lob.stockexchange.events;


import ca.yorku.cmg.lob.stockexchange.tradingagent.INewsObserver;



	/*
	 * Subject interface for the Observer pattern allowing TradingAgents to register and get notified of the updates.
	 * 
	 */
	
	public interface INewsSubject {
		void registerObserver(INewsObserver observer);
		void removeObserver(INewsObserver observer);
		void notifyObservers(Event e);
	}
	

