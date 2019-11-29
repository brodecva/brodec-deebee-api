package net.brodec.sandbox.deebee.api;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class DefaultIdGeneratingService implements IdGeneratingService {

	@Override
	public UUID generate() {
		return UUID.randomUUID();
	}

}
