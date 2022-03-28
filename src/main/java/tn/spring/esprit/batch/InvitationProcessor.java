package tn.spring.esprit.batch;

import org.springframework.batch.item.ItemProcessor;


import tn.spring.esprit.entities.Invitation;

public class InvitationProcessor implements ItemProcessor<Invitation, Invitation> {
	/*11. logique métier de notre job*/
	@Override
	public Invitation process(Invitation invitation) {
		
		invitation.setInvitationStatus(invitation.getInvitationStatus());
		invitation.setInvitationType(invitation.getInvitationType());
		invitation.setSendingDate(invitation.getSendingDate());

		
		return invitation;
	}
}
