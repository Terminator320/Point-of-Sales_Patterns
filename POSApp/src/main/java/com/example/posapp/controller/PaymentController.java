package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.PaymentFactory.PaymentFactoryClass;
import com.example.posapp.PaymentFactory.PaymentProcessing;
import com.example.posapp.models.Payment;
import com.example.posapp.models.SalesOrder;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.posapp.models.SalesOrder.*;


public class PaymentController {

    //METHOD PAYMENT SECTION
    @FXML private ChoiceBox<Payment.PaymentMethod> choiceOfPayment;

    //USER INFORMATION SECTION
    @FXML private TextField nameField;
    @FXML private TextField cardNumberField;
    @FXML private TextField threeDigitCardNumber;
    @FXML private ComboBox<String> selectMonthExpiration;
    @FXML private ComboBox<String> selectYearExpiration;

    //TIP SECTION
    @FXML private Label amount10PercentTip;
    @FXML private Label amount15PercentTip;
    @FXML private Label amount20PercentTip;
    @FXML private Label amountCustomTip;
    @FXML private ToggleGroup tipGroup;
    @FXML private TextField customTipField;
    @FXML private RadioButton radioButton10percent;
    @FXML private RadioButton radioButton15percent;
    @FXML private RadioButton radioButton20percent;
    @FXML private RadioButton radioButtonCustom;
    @FXML private RadioButton noTip;

    //CHECKOUT PAYMENT OR CANCEL PAYMENT
    @FXML private Button payButton;
    @FXML private Button cancelButton;

    //DISPLAY FINAL TOTAL AMOUNT
    @FXML private ListView<String> finalTotal;

    //LOGGER FILE
    private static final Logger LOGGER = LogConfig.getLogger(PaymentController.class.getName());


    private SalesOrderController mySalesOrder;
    private int orderID;
    private double subtotal = 0;
    private double tipAmount = 0;
    private Payment.PaymentMethod selectPaymentMethod = Payment.PaymentMethod.CASH;

    private SalesOrderController salesOrderController;

    public void setSalesOrderController(SalesOrderController controller) {
        this.salesOrderController = controller;
    }

    @FXML
    public void initialize() {
        setUpPaymentMethod();
        expirationDateChoice();
        showTipAmountLabel();
        setListOfCost();
    }

    public static void loadPopItems(HashMap<Integer, SalesOrder> items) {
        for (Map.Entry<Integer, SalesOrder> item : items.entrySet()) {
            int id = item.getValue().getId();
            int quantity = item.getValue().getQuantity();
            updateQuantityItems(id, quantity);
        }
    }

    //
    public void setSalesOrderTotal(SalesOrderController salesOrderController, int orderID){
        this.mySalesOrder = salesOrderController;
        this.orderID = orderID;
        this.subtotal = mySalesOrder.getSubtotalAsDouble();

        showTipAmountLabel();
        setListOfCost();
    }

    //METHOD PAYMENT
    //populate ChoiceBox with available payment methods
    private void setUpPaymentMethod(){
        for(Payment.PaymentMethod paymentMethod : Payment.PaymentMethod.values()){
            choiceOfPayment.getItems().addAll(paymentMethod);
        }
        choiceOfPayment.setValue(null);
        choiceOfPayment.setOnAction(event -> handlePaymentMethod());
        handlePaymentMethod();
    }

    //helper method to clear all payment input fields
    private void clearAllField(){
        nameField.clear();
        cardNumberField.clear();
        threeDigitCardNumber.clear();
        selectMonthExpiration.getSelectionModel().clearSelection();
        selectYearExpiration.getSelectionModel().clearSelection();
    }

    //helper method to enable/disable user card information fields based on their
    //selected payment method
    private void setCardInfoVisibility(boolean visible){
        double opacityLvl = visible ? 1.0 : 0.5;

        nameField.setOpacity(opacityLvl);
        nameField.setDisable(!visible);

        cardNumberField.setOpacity(opacityLvl);
        cardNumberField.setDisable(!visible);

        threeDigitCardNumber.setOpacity(opacityLvl);
        threeDigitCardNumber.setDisable(!visible);

        selectMonthExpiration.setOpacity(opacityLvl);
        selectMonthExpiration.setDisable(!visible);

        selectYearExpiration.setOpacity(opacityLvl);
        selectYearExpiration.setDisable(!visible);
    }

    //Handle UI based on selected payment method
    private void handlePaymentMethod(){
        Payment.PaymentMethod methodSelected = choiceOfPayment.getValue();

        if (methodSelected == null) {
            setCardInfoVisibility(false);
            return;
        }

        switch (methodSelected){
            case CREDIT:
                selectPaymentMethod = Payment.PaymentMethod.CREDIT;
                setCardInfoVisibility(true);
                clearAllField();
                break;
            case DEBIT:
                selectPaymentMethod = Payment.PaymentMethod.DEBIT;
                setCardInfoVisibility(true);
                clearAllField();
                break;
            default:
                selectPaymentMethod = Payment.PaymentMethod.CASH;
                setCardInfoVisibility(false);
                clearAllField();
                break;
        }
    }


    //USER INFORMATION
    //check if a text field is empty
    private boolean isFieldEmpty(TextField text){
        return text.getText().isEmpty();
    }

    //check if the card number contains 16 digits
    private boolean isCardNumValid(String cardNum){
        String removeSpaces = cardNum.replaceAll("\\s+", "");
        return removeSpaces.matches("\\d{16}");
    }

    //check if card name contains letters and spaces
    private boolean isCardNameValid(String cardName){
        return cardName.matches("[a-zA-Z ]+");
    }

    //check if the CVV contains 3 digits
    private boolean isCVVValid(String cvv){
        return cvv.matches("\\d{3}");
    }

    //check if expiration date is not already expired
    private boolean isDateValid(){
        if(isDateEmpty()){
            return false;
        }
        int month = Integer.parseInt(selectMonthExpiration.getValue());
        int year = Integer.parseInt(selectYearExpiration.getValue());

        YearMonth expirationYearMonth = YearMonth.of(year, month);
        YearMonth currentYearMonth = YearMonth.now();
        return expirationYearMonth.equals(currentYearMonth) || expirationYearMonth.isAfter(currentYearMonth);
    }

    //check if month and year is not selected
    private boolean isDateEmpty(){
        return selectMonthExpiration.getValue() == null
                || selectYearExpiration.getValue() == null;
    }

    //populate ComboBox with months (1-12) and years (current year up to 15 years in the future)
    private void expirationDateChoice(){
        for (int i = 1; i <= 12; i++) {
            selectMonthExpiration.getItems().add(String.format("%02d", i));
        }

        int year = LocalDate.now().getYear();
        for(int i = year; i < year + 15; i++) {
            selectYearExpiration.getItems().add(String.valueOf(i));
        }
    }


    @FXML
    protected void onNameFieldAction(ActionEvent event){
        if(selectPaymentMethod == Payment.PaymentMethod.CREDIT || selectPaymentMethod == Payment.PaymentMethod.DEBIT) {
            if (!isCardNameValid(nameField.getText())) {
                showInvalidInformation("Invalid name. Must only contain alphabets.");
                nameField.clear();
            }
        }
    }

    @FXML
    protected void onCardNumFieldAction(ActionEvent event){
        if(selectPaymentMethod == Payment.PaymentMethod.CREDIT || selectPaymentMethod == Payment.PaymentMethod.DEBIT) {
            if (!isCardNumValid(cardNumberField.getText())) {
                showInvalidInformation("Invalid card number. Please enter a valid card number (16 digits).");
                cardNumberField.clear();
            }
        }
    }

    @FXML
    protected void onCVVFieldAction(ActionEvent event){
        if(selectPaymentMethod == Payment.PaymentMethod.CREDIT || selectPaymentMethod == Payment.PaymentMethod.DEBIT) {
            if (!isCVVValid(threeDigitCardNumber.getText())) {
                showInvalidInformation("Invalid CVV. Please make sure it only contains 3 digits.");
                threeDigitCardNumber.clear();
            }
        }
    }

    @FXML
    protected void onExpirationDateAction(ActionEvent event){
        if(selectPaymentMethod == Payment.PaymentMethod.CREDIT || selectPaymentMethod == Payment.PaymentMethod.DEBIT) {
            if (!isDateEmpty()) {
                if (isDateValid()) {
                    showInvalidInformation("Invalid card date. Card already expired.");
                }
            }
        }
    }


    //TIPS SECTION
    @FXML
    protected void getTipPercentage(ActionEvent actionEvent) {
        if(radioButton10percent.isSelected()){
            tipAmount = calculateTip(0.10);
            customTipField.setDisable(true);
            customTipField.clear();
        }else if (radioButton15percent.isSelected()){
            tipAmount = calculateTip(0.15);
            customTipField.setDisable(true);
            customTipField.clear();
        } else if (radioButton20percent.isSelected()) {
            tipAmount = calculateTip(0.20);
            customTipField.setDisable(true);
            customTipField.clear();
        }else if (radioButtonCustom.isSelected()){
            isCustomAmountValid();
            customTipField.setDisable(false);
            customTipField.requestFocus();
        }else{
            tipAmount = 0;
            customTipField.setDisable(true);
            customTipField.clear();
        }
        setListOfCost();
    }

    @FXML
    protected void onCustomTipAction(ActionEvent event){
        isCustomAmountValid();
        setListOfCost();
    }

    private double calculateTip(double percentage){
        return subtotal * percentage;
    }

    private String formatMoney(double money){
        return String.format("$%.2f", money);
    }

    private void showTipAmountLabel(){
        double tip10 = calculateTip(0.10);
        double tip15 = calculateTip(0.15);
        double tip20 = calculateTip(0.20);

        amount10PercentTip.setText(formatMoney(tip10));
        amount15PercentTip.setText(formatMoney(tip15));
        amount20PercentTip.setText(formatMoney(tip20));
    }

    private void isCustomAmountValid(){
        try {
            String text = customTipField.getText();
            if (!text.isEmpty()) {
                tipAmount = Double.parseDouble(text);
            } else {
                tipAmount = 0;
            }
        } catch (NumberFormatException e) {
            tipAmount = 0;
            showInvalidInformation("Invalid tip amount. Please enter a valid number.");
            customTipField.clear();
        }
    }


    //LIST SHOWING PAYMENT DETAILS SUCH AS SUBTOTAL, TIPS, TAXES, AND TOTAL
    private double calculateTotal(){
        double taxes = calculateTaxes();
        return subtotal + taxes + tipAmount;
    }

    private double calculateTaxes(){
        return subtotal * 0.15;
    }

    private void setListOfCost() {
        finalTotal.setMouseTransparent(true);
        finalTotal.setFocusTraversable(false);
        finalTotal.getItems().clear();

        finalTotal.getItems().addAll(
                "Subtotal: " + formatMoney(subtotal),
                "Tips: " + formatMoney(tipAmount),
                "Taxes (15%): " + formatMoney(calculateTaxes()),
                "________________________________",
                "Total: " + formatMoney(calculateTotal())
        );
    }


    //ACTION BUTTONS FOR CREATING/CANCELLING A PAYMENT
    @FXML
    protected void onCancelAction(ActionEvent event) {
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posapp/main-view.fxml"));
            Parent newRoot = loader.load();
            Scene newScene = new Scene(newRoot);

            // Get the current stage (e.g., from a component's scene and window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.setTitle("Main menu");
            stage.show();

            cancelledOrder(orderID);
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while trying to proceed to payment view.");
        }
    }

    @FXML
    protected void OnPayAction(ActionEvent event) {

        if(choiceOfPayment.getValue() == null){
            showErrorInformation("Please select a payment method");
            return;
        }

        if(choiceOfPayment.getValue() == Payment.PaymentMethod.CREDIT || choiceOfPayment.getValue() == Payment.PaymentMethod.DEBIT) {
            if (isFieldEmpty(nameField) || isFieldEmpty(cardNumberField) || isFieldEmpty(threeDigitCardNumber) || isDateEmpty()) {
                showErrorInformation("At least one field is empty. Please fill out all fields.");
                return;
            }
        }

        if(isDateValid()){
            showErrorInformation("Date is expired. Cannot proceed with payment");
            return;
        }

        Payment createAPayment = createPaymentInfo();
        boolean successfulPayment = processPayment(createAPayment);

        if (successfulPayment) {

            //for factory
            PaymentProcessing paymentFactory = PaymentFactoryClass.createPayment(choiceOfPayment.getValue());
            String msg = paymentFactory.processPayment();

            showProcessingInfo(msg);

            //update the sales order
            SalesOrder.finalizeSale(orderID, "CLOSED", String.valueOf(LocalDateTime.now()), subtotal, calculateTaxes(), calculateTotal());

            HashMap<Integer, SalesOrder> map = salesOrderController.getPopularItemsSaleMap();
            loadPopItems(map);

            //subtractQuantity();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }else{
            showErrorInformation("Payment failed");
        }
    }

    private Payment createPaymentInfo(){
        return new Payment(
                orderID,
                choiceOfPayment.getValue(),
                tipAmount,
                LocalDateTime.now()
        );
    }

    private boolean processPayment(Payment payment){
        return payment.insertPayment();
    }


    //ALERTS SECTION
    private void alertInformation(Alert.AlertType type, String alertTitle, String alertMessage){
        Alert myAlert = new Alert(type);
        myAlert.setTitle(alertTitle);
        myAlert.setHeaderText(null);
        myAlert.setContentText(alertMessage);
        myAlert.show();
    }

    private void showConfirmation(String message){
        alertInformation(Alert.AlertType.CONFIRMATION, "Cancel Payment", message);
    }

    private void showSuccessInformation(){
        alertInformation(Alert.AlertType.INFORMATION, "Successful Payment", "Payment Successful. Thank you for your purchase!");
    }

    private void showErrorInformation(String message) {
        alertInformation(Alert.AlertType.ERROR, "Payment Error", message);
    }

    private void showInvalidInformation(String message){
        alertInformation(Alert.AlertType.WARNING, "Invalid Input", message);
    }

    private void showProcessingInfo(String message){

        Alert processingAlert = new Alert(Alert.AlertType.INFORMATION);
        processingAlert.setTitle("Payment process");
        processingAlert.setHeaderText(null);
        processingAlert.setContentText(message);
        processingAlert.show();

        // Wait 1 second, then close this and show success
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event2 -> {
            processingAlert.close();

            showSuccessInformation();
        });
        delay.play();
    }
}
