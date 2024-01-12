package uz.ilmnajot.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.todo.entity.Task;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTaskByName(String name);

    Optional<Task> findAllByCompletedTrue();
    Optional<Task> findAllByCompletedFalse();
}
