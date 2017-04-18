package contactList.app.service.avatar;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by комп on 10.04.2017.
 */
public class AvatarHandler {

    public static Byte[] getBytesFromPhoto(MultipartFile avatar){
        Byte[] avatarInBytes = null;

        try {
            avatarInBytes = ArrayUtils.toObject(avatar.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return avatarInBytes;
    }
}
