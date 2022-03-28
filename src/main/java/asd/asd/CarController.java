package asd.asd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            Car car = carRepository.findById(id).get();
            return ResponseEntity.ok(car);
        }
    }
}
