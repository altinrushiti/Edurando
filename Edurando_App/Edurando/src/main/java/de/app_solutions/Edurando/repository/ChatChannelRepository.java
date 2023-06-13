package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.ChatChannel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface ChatChannelRepository extends CrudRepository<ChatChannel,String> {

    @Query("Select c from ChatChannel c where  c.userOne.id IN (:userOneId, :userTwoId) and c.userTwo.id IN (:userOneId, :userTwoId) ")
    List<ChatChannel> findExistingChannel(
            @Param("userOneId") long userOneId, @Param("userTwoId") long userTwoId);

    @Query(" FROM"
            + "    ChatChannel c"
            + "  WHERE"
            + "    c.uuid like :uuid")
     ChatChannel getChannelDetails(@Param("uuid") String uuid);
}
