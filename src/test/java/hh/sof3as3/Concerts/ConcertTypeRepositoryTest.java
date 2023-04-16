package hh.sof3as3.Concerts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.Concerts.domain.ConcertType;
import hh.sof3as3.Concerts.domain.ConcertTypeRepository;

@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class ConcertTypeRepositoryTest {
	
	@Autowired
	private ConcertTypeRepository concertTypeRepository;
	
	@Test  // testataan ConcertTypeRepositoryn findById()-metodin toimivuutta
    public void findByNameShouldReturnConcertTypeId() {
        List<ConcertType> concertTypes = concertTypeRepository.findByConcertTypeName("Festival");
        
        assertThat(concertTypes).hasSize(1);
        assertThat(concertTypes.get(0).getConcertType_id()).isEqualTo(1);    }
	
	 @Test // testataan ConcertTypeRepositoryn save()-metodin toimivuutta
	    public void createNewConcertType() {

	    	ConcertType concertType = new ConcertType("Acoustic");
	    	concertTypeRepository.save(concertType); // SQL Insert
	    	assertThat(concertType.getConcertType_id()).isNotNull();
	    } 
	 
	 @Test // testataan ConcertTypeRepositoryn deleteById()-metodin toimivuutta
	    public void deleteConcertType() {
	    	concertTypeRepository.deleteById((long)1); 
	    	
	  
	    }  
	
}