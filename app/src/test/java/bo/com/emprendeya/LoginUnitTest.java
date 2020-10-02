package bo.com.emprendeya;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.model.users.UserProfile;
import bo.com.emprendeya.repository.MockRepository;
import bo.com.emprendeya.repository.RepositoryImpl;
import bo.com.emprendeya.utils.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LoginUnitTest {

    RepositoryImpl repository;

    @Before
    public void beforeEach() {
        repository = new MockRepository();
    }

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void loginWithRegularUser() {
        String email = "paola.rivas@email.com";
        String password = "test123";
        LiveData<Base<User>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertNull(userBase.getException());//No Errors
            assertEquals(UserProfile.REGULAR, userBase.getData().getProfile());
            assertEquals("1", userBase.getData().getUuid());
            assertEquals("Paola Rivas", userBase.getData().getDisplayName());
        });
    }

    @Test
    public void loginFailed() {
        String email = "elpezloco@email.com"; //@ y que tenga . con extension
        String password = "test123";

        LiveData<Base<User>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_LOGIN, userBase.getErrorCode());
        });
    }

    @Test
    public void loginFailedInvalidEmail() {
        String email = "elpezloco"; //@ y que tenga . con extension
        String password = "test123";

        LiveData<Base<User>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_INVALID_EMAIL, userBase.getErrorCode());
        });
    }

    @Test
    public void loginFailedEmptyValues() {
        String email = "";
        String password = "test123";

        LiveData<Base<User>> result = repository.loginWithEmailPassword(email, password);
        assertNotNull(result);

        result.observeForever(userBase -> {
            //userBase: Base<User>
            assertFalse(userBase.isSuccess());
            assertEquals(Constants.ERROR_EMPTY_VALUES, userBase.getErrorCode());
        });
    }
}
