package com.example.posapp.controller;

import com.example.posapp.LogConfig;
import com.example.posapp.models.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    private static final Logger LOGGER = LogConfig.getLogger(PaymentController.class.getName());


    private SalesOrderController mySalesOrder;
    private int orderID;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal tipAmount = BigDecimal.ZERO;
    private Payment.PaymentMethod selectPaymentMethod = Payment.PaymentMethod.CASH;



    @FXML
    public void initialize() {
        setUpPaymentMethod();
        expirationDateChoice();
        showTipAmountLabel();
        setListOfCost();
    }

    public void setSalesOrderTotal(SalesOrderController salesOrderController, int orderID){
        this.mySalesOrder = salesOrderController;
        this.orderID = orderID;
        this.subtotal = mySalesOrder.getSubtotal();

        showTipAmountLabel();
        setListOfCost();
    }

    //METHOD PAYMENT
    private void setUpPaymentMethod(){
        for(Payment.PaymentMethod paymentMethod : Payment.PaymentMethod.values()){
            choiceOfPayment.getItems().addAll(paymentMethod);
        }
        choiceOfPayment.setValue(null);
        choiceOfPayment.setOnAction(event -> handlePaymentMethod());
        handlePaymentMethod();
    }

    private void clearAllField(){
        nameField.clear();
        cardNumberField.clear();
        threeDigitCardNumber.clear();
        selectMonthExpiration.getSelectionModel().clearSelection();
        selectYearExpiration.getSelectionModel().clearSelection();
    }

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


    //USER INFORMATION
    private boolean isFieldEmpty(TextField text){
        return text.getText().isEmpty();
    }

    private boolean isCardNumValid(String cardNum){
        String removeSpaces = cardNum.replaceAll("\\s+", "");
        return removeSpaces.matches("\\d{16}");
    }

    private boolean isCardNameValid(String cardName){
        return cardName.matches("[a-zA-Z ]+");
    }

    private boolean isCVVValid(String cvv){
        return cvv.matches("\\d{3}");
    }

    private boolean isDateValid(){
        if(!isDateEmpty()){
            return false;
        }
        int month = Integer.parseInt(selectMonthExpiration.getValue());
        int year = Integer.parseInt(selectYearExpiration.getValue());

        YearMonth expirationYearMonth = YearMonth.of(year, month);
        YearMonth currentYearMonth = YearMonth.now();
        return !expirationYearMonth.isBefore(currentYearMonth);
    }

    private boolean isDateEmpty(){
        return selectMonthExpiration.getValue() != null
                && selectYearExpiration.getValue() != null;
    }

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
            if (isDateEmpty()) {
                if (!isDateValid()) {
                    showInvalidInformation("Invalid card date. Card already expired.");
                }
            }
        }
    }


    //TIPS SECTION
    @FXML
    protected void getTipPercentage(ActionEvent actionEvent) {
        if(radioButton10percent.isSelected()){
            tipAmount = calculateTip(new BigDecimal("0.10"));
            customTipField.setDisable(true);
            customTipField.clear();
        }else if (radioButton15percent.isSelected()){
            tipAmount = calculateTip(new BigDecimal("0.15"));
            customTipField.setDisable(true);
            customTipField.clear();
        } else if (radioButton20percent.isSelected()) {
            tipAmount = calculateTip(new BigDecimal("0.20"));
            customTipField.setDisable(true);
            customTipField.clear();
        }else if (radioButtonCustom.isSelected()){
            isCustomAmountValid();
            customTipField.setDisable(false);
            customTipField.requestFocus();
        }else{
            tipAmount = BigDecimal.ZERO;
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

    private BigDecimal calculateTip(BigDecimal percentage){
        return subtotal.multiply(percentage).setScale(2, RoundingMode.HALF_UP);
    }

    private String formatMoney(BigDecimal money){
        return String.format("$%.2f", money);
    }

    private void showTipAmountLabel(){
        BigDecimal tip10 = calculateTip(new BigDecimal("0.10"));
        BigDecimal tip15 = calculateTip(new BigDecimal("0.15"));
        BigDecimal tip20 = calculateTip(new BigDecimal("0.20"));

        amount10PercentTip.setText(formatMoney(tip10));
        amount15PercentTip.setText(formatMoney(tip15));
        amount20PercentTip.setText(formatMoney(tip20));
    }

    private void isCustomAmountValid(){
        try {
            String text = customTipField.getText();
            if (!text.isEmpty()) {
                tipAmount = new BigDecimal(text).setScale(2, RoundingMode.HALF_UP);
            } else {
                tipAmount = BigDecimal.ZERO;
            }
        } catch (NumberFormatException e) {
            tipAmount = BigDecimal.ZERO;
            showInvalidInformation("Invalid tip amount. Please enter a valid number.");
            customTipField.clear();
        }
    }


    //LIST SHOWING PAYMENT DETAILS SUCH AS SUBTOTAL, TIPS, TAXES, AND TOTAL
    private BigDecimal calculateTotal(){
        BigDecimal taxes = calculateTaxes();
        return subtotal.add(tipAmount).add(taxes);
    }

    private BigDecimal calculateTaxes(){
        return subtotal.multiply(new BigDecimal("0.15"));
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
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while trying to proceed to payment view.");
        }
    }

    @FXML
    protected void OnPayAction(ActionEvent event) {

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
        myAlert.showAndWait();
    }

    private void showConfirmation(String message){
        alertInformation(Alert.AlertType.CONFIRMATION, "Cancel Payment", message);
    }

    private void showSuccessInformation(String message){
        alertInformation(Alert.AlertType.INFORMATION, "Successful Payment", message);
    }

    private void showErrorInformation(String message) {
        alertInformation(Alert.AlertType.ERROR, "Payment Error", message);
    }

    private void showInvalidInformation(String message){
        alertInformation(Alert.AlertType.WARNING, "Invalid Input", message);
    }
}
