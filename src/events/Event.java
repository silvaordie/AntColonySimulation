package events;

/**
 * Interface that defines an Event's behaviour
 * @author José
 *
 */
public interface Event {
	/**
	 * Simulates the event and returns the next events to simulate
	 * @return Next events to simulate
	 */
	public abstract Event[] simulate();
	/**
	 * Returns the Event's final instant
	 * @return Event's final instant
	 */
	public abstract float getTime();
	/**
	 * Compares two events
	 * @param event Event to compare to
	 * @return Result
	 */
	public abstract int compareTo(Event event);
	/**
	 * Checks if two events are equal
	 * @param obj Event to compare to
	 * @return Result
	 */
	public abstract boolean equals(Object obj);
}
