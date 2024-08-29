package ssd.uz.llc2.db.repository.file;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssd.uz.llc2.db.domain.attachment.AttachmentContent;

import java.util.UUID;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
}

