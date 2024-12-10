package microviaje.repository.mongodb;
import microviaje.entity.Pausa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PausaRepository extends MongoRepository<Pausa, Integer> {

}
