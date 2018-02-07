package web.action;

import constant.InfoMessages;
import org.apache.log4j.Logger;
import web.action.country.*;
import web.action.hotel.*;
import web.action.order.*;
import web.action.resort.*;
import web.action.tour.*;

import java.util.HashMap;
import java.util.Map;

public class ActionContainer {

    private static final Logger LOGGER = Logger.getLogger(ActionContainer.class);

    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("login", new LoginAction());
        actions.put("logout", new LogoutAction());
        actions.put("registrationPage", new ViewRegistrationPageAction());
        actions.put("registration", new RegistrationAction());
        actions.put("profilePage", new ProfilePageAction());

        actions.put("usersPage", new ViewUsersPageAction());
        actions.put("blockOrUnblockUser", new BlockOrUnblockUserAction());

        actions.put("countriesPage", new CountriesPageAction());
        actions.put("deleteCountry", new DeleteCountryAction());
        actions.put("editCountry", new EditCountryAction());
        actions.put("editCountyPage", new EditCountryPageAction());
        actions.put("addCountryPage", new AddCountryPageAction());
        actions.put("addCountry", new AddCountryAction());

        actions.put("deleteResort", new DeleteResortAction());
        actions.put("addResortPage", new AddResortPageAction());
        actions.put("addResort", new AddResortAction());
        actions.put("resortsPage", new ResortsPageAction());
        actions.put("editResortPage", new EditResortPageAction());
        actions.put("editResort", new EditResortAction());

        actions.put("hotelsPage", new HotelsPageAction());
        actions.put("deleteHotel", new DeleteHotelAction());
        actions.put("addHotelPage", new AddHotelPageAction());
        actions.put("addHotel", new AddHotelAction());
        actions.put("editHotelPage", new EditHotelPageAction());
        actions.put("editHotel", new EditHotelAction());

        actions.put("toursPage", new ToursPageAction());
        actions.put("tourInfoPage", new TourInfoPage());
        actions.put("deleteTour", new DeleteTourAction());
        actions.put("addTourPage", new AddTourPageAction());
        actions.put("editTourPage", new EditTourPageAction());
        actions.put("editTour", new EditTourAction());
        actions.put("setHotTour", new SetHotTourAction());
        actions.put("searchTour", new SearchTourAction());

        actions.put("orderPage", new MakeOrderPageAction());
        actions.put("addOrder", new MakeOrderAction());
        actions.put("ordersPage", new OrdersPageAction());
        actions.put("changeOrderStatus", new ChangeOrderStatusAction());
        actions.put("addDiscountToOrder", new AddDiscountToOrderAction());

        actions.put("noActions", new NoAction());
    }

    public static Action getWebAction(String actionName) {
        if (actionName == null || !actions.containsKey(actionName)) {
            LOGGER.trace(InfoMessages.INFO_ACTION_NOT_FOUND);
            return actions.get("noActions");
        }
        return actions.get(actionName);
    }

}
