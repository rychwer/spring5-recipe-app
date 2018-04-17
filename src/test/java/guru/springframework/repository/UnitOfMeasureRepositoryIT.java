package guru.springframework.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository uom;
	
	@Test
	@DirtiesContext
	public void testFindByDescription() {
		
		Optional<UnitOfMeasure> unitOfMeasure = this.uom.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", unitOfMeasure.get().getDescription());
		
	}
	
	@Test
	public void testFindByDescriptionCup() {
		
		Optional<UnitOfMeasure> unitOfMeasure = this.uom.findByDescription("Cup");
		
		assertEquals("Cup", unitOfMeasure.get().getDescription());
		
	}

}
