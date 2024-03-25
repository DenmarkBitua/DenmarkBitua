import java.util.Stack;

public class MathMateCalculator extends javax.swing.JFrame {

    private boolean operatorAdded = false;
    public MathMateCalculator() {
        initComponents();
       String data = "tan(3)";
    double result = evaluateExpression(data); 
    System.out.println(result);
        
    }

    private static String originalEquation;


private static double evaluateExpression(String expression) {
    
    originalEquation = expression;
    
    char[] tokens = expression.toCharArray();

    Stack<Double> values = new Stack<>();
    Stack<String> operators = new Stack<>();

    for (int i = 0; i < tokens.length; i++) {
        if (tokens[i] == ' ')
            continue;

        if (Character.isDigit(tokens[i]) || tokens[i] == '.') {
            StringBuilder sb = new StringBuilder();
            while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                sb.append(tokens[i]);
                i++;
            }
            i--;
            double value = Double.parseDouble(sb.toString());
            values.push(value);
        } else if (Character.isAlphabetic(tokens[i])) { 
            StringBuilder sb = new StringBuilder();
            while (i < tokens.length && Character.isAlphabetic(tokens[i])) {
                sb.append(tokens[i]);
                i++;
            }
            i--;

            String functionName = sb.toString().toLowerCase();
            if ("sin".equals(functionName) || "cos".equals(functionName) || "tan".equals(functionName)) {
                operators.push(functionName);
            } else {
                throw new IllegalArgumentException("Unknown function: " + functionName);
            }
        } else if (tokens[i] == '(') {
            if (i > 0 && (Character.isDigit(tokens[i - 1]) || tokens[i - 1] == ')')) {
                operators.push("*"); // Implicit multiplication
            }
            operators.push(String.valueOf(tokens[i]));
        } else if (tokens[i] == ')') {
            while (!operators.isEmpty() && !operators.peek().equals("(")) {
                String operator = operators.pop();
                if ("sin".equals(operator) || "cos".equals(operator) || "tan".equals(operator)) {
                    double operand = values.pop();
                    double result = computeTrigonometric(operator, operand);
                    values.push(result);
                } else {
                    double b = values.pop();
                    double a = values.pop();
                    double result = applyOperator(operator.charAt(0), b, a);
                    values.push(result);
                }
            }
            if (operators.isEmpty() || !operators.peek().equals("(")) {
                throw new IllegalArgumentException("Mismatched parentheses in expression: " + expression);
            }
            operators.pop(); // Pop the '('
        } else if (isOperator(tokens[i])) {
            while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek().charAt(0))) {
                String operator = operators.pop();
                if ("sin".equals(operator) || "cos".equals(operator) || "tan".equals(operator)) {
                    double operand = values.pop();
                    double result = computeTrigonometric(operator, operand);
                    values.push(result);
                } else {
                    double b = values.pop();
                    double a = values.pop();
                    double result = applyOperator(operator.charAt(0), b, a);
                    values.push(result);
                }
            }
            operators.push(String.valueOf(tokens[i]));
        } else if (tokens[i] == '%') {
            values.push(values.pop() / 100.0);
        }
    }

    while (!operators.isEmpty()) {
        String operator = operators.pop();
        if ("sin".equals(operator) || "cos".equals(operator) || "tan".equals(operator)) {
            double operand = values.pop();
            double result = computeTrigonometric(operator, operand);
            values.push(result);
        } else {
            double b = values.pop();
            double a = values.pop();
            double result = applyOperator(operator.charAt(0), b, a);
            values.push(result);
        }
    }
    
    return values.pop();
    
}
private static double computeTrigonometric(String functionName, double operand) {
   
    double angle = functionName.equals("sin") || functionName.equals("cos") || functionName.equals("tan") ?
            Math.toRadians(operand) : operand;

    switch (functionName) {
        case "sin":
            return Math.sin(angle);
        case "cos":
            return Math.cos(angle);
        case "tan":
            return Math.tan(angle);
        default:
            throw new IllegalArgumentException("Invalid function: " + functionName);
    }
}
private static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
}

private static boolean hasPrecedence(char op1, char op2) {
    if (op2 == '(' || op2 == ')') {
        return false;
    }
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
        return false;
    }
    return getPrecedence(op1) <= getPrecedence(op2);
}


private static int getPrecedence(char operator) {
    switch (operator) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        default:
            return 0;
    }
}


private static double applyOperator(char operator, double b, double a) {
    switch (operator) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        case '^':
            return Math.pow(a, b);
        default:
            throw new IllegalArgumentException("Invalid operator: " + operator);
    }
}


    private boolean isValidNumericExpression(String expression) {
    try {
        
        Double.parseDouble(expression);
        return true;
    } catch (NumberFormatException e) {
        
        return false;
    }
}
    private boolean isLastCharacterNumber(String text) {
    int len = text.length();
    return len > 0 && Character.isDigit(text.charAt(len - 1));
}
    private boolean hasDecimalPoint(String text) {
    return text.contains(".");
}
    private boolean hasOperator(String text) {
    
    return !text.isEmpty() && isOperator(text.charAt(text.length() - 1));
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Solution = new javax.swing.JTextField();
        Percentage = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Tangent = new javax.swing.JButton();
        Cosine = new javax.swing.JButton();
        Sine = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        ClosePar = new javax.swing.JButton();
        OpenPar = new javax.swing.JButton();
        Subtraction = new javax.swing.JButton();
        Addition = new javax.swing.JButton();
        Multiplication = new javax.swing.JButton();
        Division = new javax.swing.JButton();
        EqualSign = new javax.swing.JButton();
        Nine = new javax.swing.JButton();
        Eight = new javax.swing.JButton();
        Seven = new javax.swing.JButton();
        Four = new javax.swing.JButton();
        Five = new javax.swing.JButton();
        Six = new javax.swing.JButton();
        One = new javax.swing.JButton();
        Decimal = new javax.swing.JButton();
        Two = new javax.swing.JButton();
        Zero = new javax.swing.JButton();
        Three = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Exponential = new javax.swing.JButton();
        ReturnEquationButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        Solution.setEditable(false);
        Solution.setBackground(new java.awt.Color(255, 255, 255));
        Solution.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        Solution.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Solution.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SolutionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SolutionFocusLost(evt);
            }
        });
        Solution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SolutionActionPerformed(evt);
            }
        });
        Solution.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SolutionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SolutionKeyTyped(evt);
            }
        });

        Percentage.setBackground(new java.awt.Color(204, 204, 204));
        Percentage.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        Percentage.setForeground(new java.awt.Color(0, 0, 0));
        Percentage.setText("%");
        Percentage.setBorder(null);
        Percentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PercentageActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(204, 204, 204));
        Delete.setFont(new java.awt.Font("Segoe UI Historic", 1, 10)); // NOI18N
        Delete.setForeground(new java.awt.Color(0, 0, 0));
        Delete.setText("Del");
        Delete.setBorder(null);
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Tangent.setBackground(new java.awt.Color(204, 204, 204));
        Tangent.setFont(new java.awt.Font("Segoe UI Historic", 1, 10)); // NOI18N
        Tangent.setForeground(new java.awt.Color(0, 0, 0));
        Tangent.setText("Tan");
        Tangent.setBorder(null);
        Tangent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TangentActionPerformed(evt);
            }
        });

        Cosine.setBackground(new java.awt.Color(204, 204, 204));
        Cosine.setFont(new java.awt.Font("Segoe UI Historic", 1, 10)); // NOI18N
        Cosine.setForeground(new java.awt.Color(0, 0, 0));
        Cosine.setText("Cos");
        Cosine.setBorder(null);
        Cosine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CosineActionPerformed(evt);
            }
        });

        Sine.setBackground(new java.awt.Color(204, 204, 204));
        Sine.setFont(new java.awt.Font("Segoe UI Historic", 1, 10)); // NOI18N
        Sine.setForeground(new java.awt.Color(0, 0, 0));
        Sine.setText("Sin");
        Sine.setBorder(null);
        Sine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SineActionPerformed(evt);
            }
        });

        Clear.setBackground(new java.awt.Color(204, 204, 204));
        Clear.setFont(new java.awt.Font("Segoe UI Historic", 1, 11)); // NOI18N
        Clear.setForeground(new java.awt.Color(0, 0, 0));
        Clear.setText("C");
        Clear.setBorder(null);
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        ClosePar.setBackground(new java.awt.Color(102, 102, 102));
        ClosePar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        ClosePar.setForeground(new java.awt.Color(255, 255, 255));
        ClosePar.setText(")");
        ClosePar.setBorder(null);
        ClosePar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseParActionPerformed(evt);
            }
        });

        OpenPar.setBackground(new java.awt.Color(102, 102, 102));
        OpenPar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        OpenPar.setForeground(new java.awt.Color(255, 255, 255));
        OpenPar.setText("(");
        OpenPar.setBorder(null);
        OpenPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenParActionPerformed(evt);
            }
        });

        Subtraction.setBackground(new java.awt.Color(102, 102, 102));
        Subtraction.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Subtraction.setForeground(new java.awt.Color(255, 255, 255));
        Subtraction.setText("-");
        Subtraction.setBorder(null);
        Subtraction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubtractionActionPerformed(evt);
            }
        });

        Addition.setBackground(new java.awt.Color(102, 102, 102));
        Addition.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Addition.setForeground(new java.awt.Color(255, 255, 255));
        Addition.setText("+");
        Addition.setBorder(null);
        Addition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdditionActionPerformed(evt);
            }
        });

        Multiplication.setBackground(new java.awt.Color(102, 102, 102));
        Multiplication.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Multiplication.setForeground(new java.awt.Color(255, 255, 255));
        Multiplication.setText("*");
        Multiplication.setBorder(null);
        Multiplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MultiplicationActionPerformed(evt);
            }
        });

        Division.setBackground(new java.awt.Color(102, 102, 102));
        Division.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Division.setForeground(new java.awt.Color(255, 255, 255));
        Division.setText("/");
        Division.setBorder(null);
        Division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DivisionActionPerformed(evt);
            }
        });

        EqualSign.setBackground(new java.awt.Color(255, 102, 0));
        EqualSign.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        EqualSign.setForeground(new java.awt.Color(255, 255, 255));
        EqualSign.setText("=");
        EqualSign.setBorder(null);
        EqualSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EqualSignActionPerformed(evt);
            }
        });

        Nine.setBackground(new java.awt.Color(0, 0, 0));
        Nine.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Nine.setForeground(new java.awt.Color(255, 255, 255));
        Nine.setText("9");
        Nine.setBorder(null);
        Nine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NineActionPerformed(evt);
            }
        });

        Eight.setBackground(new java.awt.Color(0, 0, 0));
        Eight.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Eight.setForeground(new java.awt.Color(255, 255, 255));
        Eight.setText("8");
        Eight.setBorder(null);
        Eight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EightActionPerformed(evt);
            }
        });

        Seven.setBackground(new java.awt.Color(0, 0, 0));
        Seven.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Seven.setForeground(new java.awt.Color(255, 255, 255));
        Seven.setText("7");
        Seven.setBorder(null);
        Seven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SevenActionPerformed(evt);
            }
        });

        Four.setBackground(new java.awt.Color(0, 0, 0));
        Four.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Four.setForeground(new java.awt.Color(255, 255, 255));
        Four.setText("4");
        Four.setBorder(null);
        Four.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FourActionPerformed(evt);
            }
        });

        Five.setBackground(new java.awt.Color(0, 0, 0));
        Five.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Five.setForeground(new java.awt.Color(255, 255, 255));
        Five.setText("5");
        Five.setBorder(null);
        Five.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiveActionPerformed(evt);
            }
        });

        Six.setBackground(new java.awt.Color(0, 0, 0));
        Six.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Six.setForeground(new java.awt.Color(255, 255, 255));
        Six.setText("6");
        Six.setBorder(null);
        Six.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SixActionPerformed(evt);
            }
        });

        One.setBackground(new java.awt.Color(0, 0, 0));
        One.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        One.setForeground(new java.awt.Color(255, 255, 255));
        One.setText("1");
        One.setBorder(null);
        One.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneActionPerformed(evt);
            }
        });

        Decimal.setBackground(new java.awt.Color(0, 0, 0));
        Decimal.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Decimal.setForeground(new java.awt.Color(255, 255, 255));
        Decimal.setText(".");
        Decimal.setBorder(null);
        Decimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecimalActionPerformed(evt);
            }
        });

        Two.setBackground(new java.awt.Color(0, 0, 0));
        Two.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Two.setForeground(new java.awt.Color(255, 255, 255));
        Two.setText("2");
        Two.setBorder(null);
        Two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwoActionPerformed(evt);
            }
        });

        Zero.setBackground(new java.awt.Color(0, 0, 0));
        Zero.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Zero.setForeground(new java.awt.Color(255, 255, 255));
        Zero.setText("0");
        Zero.setBorder(null);
        Zero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZeroActionPerformed(evt);
            }
        });

        Three.setBackground(new java.awt.Color(0, 0, 0));
        Three.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        Three.setForeground(new java.awt.Color(255, 255, 255));
        Three.setText("3");
        Three.setBorder(null);
        Three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThreeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 2, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("MathMate Calculator");

        Exponential.setBackground(new java.awt.Color(204, 204, 204));
        Exponential.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        Exponential.setForeground(new java.awt.Color(0, 0, 0));
        Exponential.setText("^");
        Exponential.setBorder(null);
        Exponential.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExponentialActionPerformed(evt);
            }
        });

        ReturnEquationButton.setBackground(new java.awt.Color(204, 204, 204));
        ReturnEquationButton.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        ReturnEquationButton.setForeground(new java.awt.Color(0, 0, 0));
        ReturnEquationButton.setText("<");
        ReturnEquationButton.setBorder(null);
        ReturnEquationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnEquationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Solution, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Four, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(One, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Five, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Six, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Two, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Three, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Zero, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Decimal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Seven, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Eight, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Nine, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EqualSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(OpenPar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Subtraction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Division, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Multiplication, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Addition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ClosePar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ReturnEquationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Sine, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cosine, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tangent, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Exponential, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Percentage, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Solution, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ReturnEquationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Sine, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Tangent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Percentage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Exponential, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(1, 1, 1))
                                        .addComponent(Cosine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Seven, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Eight, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Nine, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ClosePar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(OpenPar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Subtraction, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Four, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Five, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Six, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(One, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Two, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(Three, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Addition, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Division, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(Multiplication, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Zero, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Decimal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EqualSign, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    
    private void OneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneActionPerformed
        String btn1 = "1";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_OneActionPerformed

    private void TwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TwoActionPerformed
     String btn1 = "2";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_TwoActionPerformed

    private void ThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThreeActionPerformed
       String btn1 = "3";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_ThreeActionPerformed

    private void ZeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZeroActionPerformed
        String btn1 = "0";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_ZeroActionPerformed

    private void FourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FourActionPerformed
      String btn1 = "4";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_FourActionPerformed

    private void SixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SixActionPerformed
       String btn1 = "6";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_SixActionPerformed

    private void SevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SevenActionPerformed
       String btn1 = "7";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_SevenActionPerformed

    private void EightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EightActionPerformed
       String btn1 = "8";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_EightActionPerformed

    private void NineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NineActionPerformed
        String btn1 = "9";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_NineActionPerformed

    private void FiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiveActionPerformed
       String btn1 = "5";
    String currentText = Solution.getText();
    Solution.setText(currentText + btn1);
    operatorAdded = false;
    }//GEN-LAST:event_FiveActionPerformed

    private void SineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SineActionPerformed
       String btn1 = "sin(";
    String currentText = Solution.getText();
    
    // Add sin function to the expression
    Solution.setText(currentText + btn1);
    }//GEN-LAST:event_SineActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
         Solution.setText("");
         Solution.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void DivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DivisionActionPerformed
       String btn1 = "/";
    String currentText = Solution.getText();
    
    
    if (!operatorAdded) {
        Solution.setText(currentText + btn1);
        operatorAdded = true;
    }
    }//GEN-LAST:event_DivisionActionPerformed

    private void MultiplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MultiplicationActionPerformed
          String btn1 = "*";
    String currentText = Solution.getText();
    
    
    if (!operatorAdded) {
        Solution.setText(currentText + btn1);
        operatorAdded = true;
    }
    }//GEN-LAST:event_MultiplicationActionPerformed

    private void SubtractionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubtractionActionPerformed
        String btn1 = "-";
    String currentText = Solution.getText();
    
   
    if (!operatorAdded) {
        Solution.setText(currentText + btn1);
        operatorAdded = true;
    }
    }//GEN-LAST:event_SubtractionActionPerformed

    private void AdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdditionActionPerformed
         String btn1 = "+";
    String currentText = Solution.getText();
    
    
    if (!operatorAdded) {
        Solution.setText(currentText + btn1);
        operatorAdded = true;
    }
    }//GEN-LAST:event_AdditionActionPerformed

    private void EqualSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EqualSignActionPerformed
      String data = Solution.getText();
      double result = evaluateExpression(data);
      String finalResult = Double.toString(result);
      Solution.setText(finalResult);
    }//GEN-LAST:event_EqualSignActionPerformed

    private void PercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PercentageActionPerformed
    String btn1 = "%";
    String currentText = Solution.getText();

   
    if (currentText.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1))) {
        
        Solution.setText(currentText + "0" + btn1);
    } else if (!currentText.endsWith(btn1) && !hasDecimalPoint(currentText)) {
        
        Solution.setText(currentText + btn1);
    }
    }//GEN-LAST:event_PercentageActionPerformed

    private void DecimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecimalActionPerformed
   String btn1 = ".";
    String currentText = Solution.getText();

    
    if (currentText.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1))) {
       
        Solution.setText(currentText + "0" + btn1);
    } else if (currentText.matches(".*\\d*\\.\\d*$")) {
       
    } else {
        
        Solution.setText(currentText + btn1);
    }      
         
    
    }//GEN-LAST:event_DecimalActionPerformed

    private void SolutionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SolutionKeyTyped
      char typedChar = evt.getKeyChar();

    
    if (Character.isDigit(typedChar) || isOperator(typedChar) || typedChar == '.') {
        String data = Solution.getText() + typedChar;
        double result = evaluateExpression(data);
        String finalResult = Double.toString(result);
        Solution.setText(finalResult);
    }
    }//GEN-LAST:event_SolutionKeyTyped

    private void SolutionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SolutionKeyReleased
 char typedChar = evt.getKeyChar();

    
    if (Character.isDigit(typedChar) || isOperator(typedChar) || typedChar == '.') {
        String data = Solution.getText() + typedChar;
        double result = evaluateExpression(data);
        String finalResult = Double.toString(result);
        Solution.setText(finalResult);
    }
    }//GEN-LAST:event_SolutionKeyReleased

    private void SolutionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SolutionFocusGained
       
    }//GEN-LAST:event_SolutionFocusGained

    private void SolutionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SolutionFocusLost
     
    }//GEN-LAST:event_SolutionFocusLost

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
  String currentText = Solution.getText();
    
    
    if (!currentText.isEmpty()) {
       
        String newText = currentText.substring(0, currentText.length() - 1);
        Solution.setText(newText);
    }
    }//GEN-LAST:event_DeleteActionPerformed

    private void TangentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TangentActionPerformed
    String btn1 = "tan(";
    String currentText = Solution.getText();
    
    
    Solution.setText(currentText + btn1);
    operatorAdded = false; // Reset t
    }//GEN-LAST:event_TangentActionPerformed

    private void CosineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CosineActionPerformed
    String btn1 = "cos(";
    String currentText = Solution.getText();
    
    
    Solution.setText(currentText + btn1);
    operatorAdded = false; // Reset t
    }//GEN-LAST:event_CosineActionPerformed

    private void CloseParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseParActionPerformed
    String btn1 = ")";
    String currentText = Solution.getText();
    
   
    if (!operatorAdded) {
        Solution.setText(currentText + btn1);
        operatorAdded = false;
    }
    }//GEN-LAST:event_CloseParActionPerformed

    private void OpenParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenParActionPerformed
       String btn1 = "(";
    String currentText = Solution.getText();
    
    
    if (!operatorAdded) {
        Solution.setText(currentText + btn1);
        operatorAdded = true;
    }
    }//GEN-LAST:event_OpenParActionPerformed

    private void ExponentialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExponentialActionPerformed
        String currentText = Solution.getText();
        
        
        Solution.setText(currentText + "^");
    }//GEN-LAST:event_ExponentialActionPerformed

    private void SolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SolutionActionPerformed
        
    }//GEN-LAST:event_SolutionActionPerformed
    
    private void ReturnEquationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnEquationButtonActionPerformed
        Solution.setText(originalEquation);
        
    }//GEN-LAST:event_ReturnEquationButtonActionPerformed

    
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MathMateCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MathMateCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MathMateCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MathMateCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MathMateCalculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Addition;
    private javax.swing.JButton Clear;
    private javax.swing.JButton ClosePar;
    private javax.swing.JButton Cosine;
    private javax.swing.JButton Decimal;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Division;
    private javax.swing.JButton Eight;
    private javax.swing.JButton EqualSign;
    private javax.swing.JButton Exponential;
    private javax.swing.JButton Five;
    private javax.swing.JButton Four;
    private javax.swing.JButton Multiplication;
    private javax.swing.JButton Nine;
    private javax.swing.JButton One;
    private javax.swing.JButton OpenPar;
    private javax.swing.JButton Percentage;
    private javax.swing.JButton ReturnEquationButton;
    private javax.swing.JButton Seven;
    private javax.swing.JButton Sine;
    private javax.swing.JButton Six;
    private javax.swing.JTextField Solution;
    private javax.swing.JButton Subtraction;
    private javax.swing.JButton Tangent;
    private javax.swing.JButton Three;
    private javax.swing.JButton Two;
    private javax.swing.JButton Zero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
