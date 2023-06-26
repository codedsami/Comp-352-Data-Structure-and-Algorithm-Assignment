package main.stuff;

public class TooFullException extends Exception {
	private String record;

	// Oh, right, let's pass the record that failed to insert into the exception.
	// Maybe someone will find it useful... or not. I mean, it's not like we have
	// much else to go on
	// when the table gets too full. Just knowing the record might shed some light
	// on things. Or not.
	// Maybe it's just an extra detail in the error message.
	public TooFullException(String record) {
		super("Table too full to insert record: " + record);
		this.record = record;
	}

	// And this getter. I guess if someone catches the exception and wants to know
	// more about the
	// record that caused the table to overflow, this getter could be helpful. But
	// really, what are
	// they going to do with it? We could probably skip this altogether... unless
	// they want to
	// reattempt the insertion later when there's space? Yeah, that could be a use
	// case...
	// or maybe it's overkill.
	public String getRecord() {
		return record;
	}
}
