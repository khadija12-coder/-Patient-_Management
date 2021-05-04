package emsi.ma;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import emsi.ma.entities.Patient;
import emsi.ma.repositories.PatientRepository;

@SpringBootApplication
public class Tp1Application implements CommandLineRunner {
    @Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		patientRepository.save(new Patient(null,"khadija",new Date(),85566,true));
		patientRepository.save(new Patient(null,"zineb",new Date(),8556626,true));
		patientRepository.save(new Patient(null,"zineb",new Date(),866,false));
		patientRepository.save(new Patient(null,"zineb",new Date(),85566,true));
		patientRepository.save(new Patient(null,"hassan",new Date(),85566,true));
		patientRepository.save(new Patient(null,"khadija",new Date(),385566,false));
		patientRepository.save(new Patient(null,"marwa",new Date(),8556626,true));
	
		
	}

}
