package tn.spring.esprit.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.spring.esprit.Service.IInvitationService;
import tn.spring.esprit.entities.Invitation;

@Slf4j
public class InvitationWriter implements ItemWriter<Invitation> {

    @Autowired
    private IInvitationService serv;

    /* écrire nos données dans la base de données*/
    @SuppressWarnings("unchecked")
	public void write(List<? extends Invitation> invs) {
            log.info("Enregistrement des lignes Invitation dans la base de données", invs);
            serv.addInvitations((List<Invitation>) invs)
            ; 
    }
}
