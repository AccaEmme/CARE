package it.unisannio.CARE.model.bloodBag;

/**
 **************************************************************************
 * ENUM Class for the various states of the request
 **************************************************************************
 */
public enum RequestState {
	
	pending,
	accepted,
	closed,
	refused,
	accepted_waiting_for_response;
}
