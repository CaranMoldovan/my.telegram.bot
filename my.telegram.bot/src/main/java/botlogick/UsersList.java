package botlogick;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
@Component
public class UsersList {
    private ArrayList<AbstractUser> users =new ArrayList<>();

    public void add(AbstractUser user){
    cleane();
    if (binarySearch(user.getID())==null) {
            users.add(user);
        }
        Collections.sort(users);
        }

    private void cleane(){
        if(users.size()>100){
        users.clear();
        }
}
        public void remove(AbstractUser user){
        users.remove(binarySearch(user.getID()));
        }


    public AbstractUser binarySearch(long userID) {//возвращает пользователя
        long targetId = userID;
        int left = 0;
        int right = users.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            AbstractUser midUser = users.get(mid);

            if (midUser.getID() == targetId) {
                return midUser; // Пользователь с указанным ID найден
            }

            if (midUser.getID() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Пользователь с указанным ID не найден
    }
    }


