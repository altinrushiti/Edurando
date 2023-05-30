package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Transactional
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,String> {

    @Query(" FROM"
            + "    ChatMessage m"
            + "  WHERE"
            + "    m.sender.id IN (:userIdOne, :userIdTwo)"
            + "  AND"
            + "    m.receiver.id IN (:userIdOne, :userIdTwo)"
            + "  ORDER BY"
            + "    m.timeSent"
            + "  DESC")
    public List<ChatMessage> getExistingChatMessages(
            @Param("userIdOne") Long userIdOne, @Param("userIdTwo") Long userIdTwo, Pageable pageable);
}
