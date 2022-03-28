package tn.spring.esprit.entities;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class InvitationFieldSetMapper implements FieldSetMapper<Invitation> {
    @Override
    public Invitation mapFieldSet(FieldSet fieldSet) {
        return Invitation.builder()
               .build();
    }
    
  
}

