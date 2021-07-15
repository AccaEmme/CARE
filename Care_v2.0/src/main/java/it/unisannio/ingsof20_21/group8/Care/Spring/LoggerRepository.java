package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerDAO, Long> {

	/**
	 * @param username the given user username
	 * @return all the logs of that specific user
	 */
	@Query("FROM LoggerDAO l WHERE l.currentUserUsername =:username")
	Iterable<LoggerDAO> filterLogsByUsername(@Param("username") String username);

	/**
	 * @param id the id to find
	 * @return the log corrisponding to the id
	 */
	@Query("FROM LoggerDAO l WHERE l.idLog =:id")
	LoggerDAO findLogById(@Param("id") long id);

	/**
	 * @param first  the first date
	 * @param second the second date
	 * @return all the logs made between the dates
	 */
	@Query("FROM LoggerDAO l WHERE l.currentTimeStamp >:first AND l.currentTimeStamp <:second")
	Iterable<LoggerDAO> filterLogsByDate(@Param("first") long first, @Param("second") long second);
}
