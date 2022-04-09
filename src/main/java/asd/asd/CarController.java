package asd.asd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//meaning it is ready for use by Spring MVC to handle web requests
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping
    public List<Car> getAll(){
        return carRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> get(@PathVariable Long id){
        System.out.println("Asked for car with id: "+id);
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            Car car = carRepository.findById(id).get();
            return ResponseEntity.ok(car);
        }
    }

    @GetMapping("add")
    public String addNewCar(){
        System.out.println("Car adding enpoint called!");
        
        Car car = new Car();
        car.setModel("Ferrari");

        carRepository.saveAll(List.of(car));
        return "Siker!";
    }
}
