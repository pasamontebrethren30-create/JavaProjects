import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/*
 * ============================================================
 * LOGIC VAULT GAME
 * ============================================================
 *
 * Java Activity: Logical Operations
 * Operators demonstrated:
 *
 * AND  -> &&
 * OR   -> ||
 * NOT  -> !
 *
 * This program uses Java Swing for the graphical user interface.
 *
 * File name:
 * LogicVaultGame.java
 *
 * ============================================================
 */

public class LogicVaultGame extends JFrame {

    // ========================================================
    // PLAYER VARIABLES
    // ========================================================

    private String playerName;

    private int hp = 100;
    private int maxHp = 100;

    private int stamina = 50;
    private int maxStamina = 50;

    private int mana = 40;
    private int maxMana = 40;

    private int level = 1;
    private int xp = 0;
    private int xpNeeded = 100;

    private int gold = 50;

    // ========================================================
    // INVENTORY / STATUS
    // ========================================================

    private boolean hasWeapon = false;
    private boolean hasKey = false;
    private boolean hasMagicGem = false;
    private boolean hasShield = false;

    private boolean cursed = false;
    private boolean enemyDefeated = false;

    // ========================================================
    // ENEMY VARIABLES
    // ========================================================

    private String enemyName = "Shadow Goblin";
    private int enemyHp = 80;
    private int enemyMaxHp = 80;
    private int enemyAttack = 12;

    // ========================================================
    // RANDOM NUMBER GENERATOR
    // ========================================================

    private Random random = new Random();

    // ========================================================
    // UI COMPONENTS
    // ========================================================

    private JLabel nameLabel;
    private JLabel levelLabel;
    private JLabel hpLabel;
    private JLabel staminaLabel;
    private JLabel manaLabel;
    private JLabel xpLabel;
    private JLabel goldLabel;

    private JLabel weaponLabel;
    private JLabel keyLabel;
    private JLabel gemLabel;
    private JLabel shieldLabel;
    private JLabel curseLabel;

    private JLabel enemyNameLabel;
    private JLabel enemyHpLabel;

    private JProgressBar hpBar;
    private JProgressBar staminaBar;
    private JProgressBar manaBar;
    private JProgressBar xpBar;
    private JProgressBar enemyHpBar;

    private JTextArea battleLog;

    private JPanel gamePanel;
    private JPanel statsPanel;
    private JPanel inventoryPanel;
    private JPanel enemyPanel;
    private JPanel actionPanel;

    // ========================================================
    // COLORS
    // ========================================================

    private final Color BACKGROUND = new Color(20, 20, 35);
    private final Color PANEL = new Color(35, 35, 55);
    private final Color PANEL_LIGHT = new Color(48, 48, 72);
    private final Color TEXT = new Color(235, 235, 245);
    private final Color GOLD = new Color(255, 205, 80);
    private final Color RED = new Color(230, 80, 80);
    private final Color GREEN = new Color(90, 210, 120);
    private final Color BLUE = new Color(90, 150, 255);
    private final Color PURPLE = new Color(170, 100, 240);

    // ========================================================
    // CONSTRUCTOR
    // ========================================================

    public LogicVaultGame() {

        setTitle("Logic Vault Game - AND OR NOT");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showStartScreen();
    }

    // ========================================================
    // START SCREEN
    // ========================================================

    private void showStartScreen() {

        getContentPane().removeAll();

        JPanel startPanel = new JPanel(new BorderLayout());
        startPanel.setBackground(BACKGROUND);
        startPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("LOGIC VAULT", SwingConstants.CENTER);
        title.setForeground(GOLD);
        title.setFont(new Font("Serif", Font.BOLD, 52));

        JLabel subtitle = new JLabel(
                "A Logic-Based Adventure Powered by AND • OR • NOT",
                SwingConstants.CENTER
        );
        subtitle.setForeground(TEXT);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 22));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(BACKGROUND);
        titlePanel.add(title);
        titlePanel.add(subtitle);

        startPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(BACKGROUND);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel story = new JLabel(
                "<html><div style='text-align:center;'>"
                + "<b>Welcome, Hero!</b><br><br>"
                + "The Logic Vault has been invaded by monsters.<br>"
                + "Use your equipment, stamina, magic, and logic<br>"
                + "to defeat the Shadow Goblin and unlock the vault."
                + "</div></html>"
        );

        story.setForeground(TEXT);
        story.setFont(new Font("SansSerif", Font.PLAIN, 18));
        story.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalStrut(50));
        center.add(story);
        center.add(Box.createVerticalStrut(40));

        JButton startButton = createButton("START ADVENTURE", GREEN);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(280, 60));
        startButton.setMaximumSize(new Dimension(280, 60));

        startButton.addActionListener(e -> startGame());

        center.add(startButton);

        center.add(Box.createVerticalStrut(25));

        JButton explanationButton = createButton(
                "HOW LOGIC IS USED",
                PURPLE
        );

        explanationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        explanationButton.setPreferredSize(new Dimension(280, 55));
        explanationButton.setMaximumSize(new Dimension(280, 55));

        explanationButton.addActionListener(e -> showLogicExplanation());

        center.add(explanationButton);

        startPanel.add(center, BorderLayout.CENTER);

        JLabel footer = new JLabel(
                "Java Swing Game • Logical Operations Activity",
                SwingConstants.CENTER
        );

        footer.setForeground(Color.GRAY);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 13));

        startPanel.add(footer, BorderLayout.SOUTH);

        getContentPane().add(startPanel);

        revalidate();
        repaint();
    }

    // ========================================================
    // START GAME
    // ========================================================

    private void startGame() {

        String input = JOptionPane.showInputDialog(
                this,
                "Enter your hero name:",
                "Create Your Hero",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input == null || input.trim().isEmpty()) {
            playerName = "Hero";
        } else {
            playerName = input.trim();
        }

        hp = 100;
        stamina = 50;
        mana = 40;
        level = 1;
        xp = 0;
        gold = 50;

        hasWeapon = false;
        hasKey = false;
        hasMagicGem = false;
        hasShield = false;
        cursed = false;
        enemyDefeated = false;

        enemyName = "Shadow Goblin";
        enemyHp = 80;

        createGameUI();

        log("Welcome, " + playerName + "!");
        log("Your adventure has begun.");
        log("Find a weapon before attempting a normal attack.");
        log("Remember: logic controls the actions!");

        updateUI();
    }

    // ========================================================
    // CREATE MAIN GAME UI
    // ========================================================

    private void createGameUI() {

        getContentPane().removeAll();

        gamePanel = new JPanel(new BorderLayout(10, 10));
        gamePanel.setBackground(BACKGROUND);
        gamePanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        createTopPanel();
        createLeftPanel();
        createCenterPanel();
        createBottomPanel();

        getContentPane().add(gamePanel);

        revalidate();
        repaint();
    }

    // ========================================================
    // TOP PANEL
    // ========================================================

    private void createTopPanel() {

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(PANEL);
        topPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel title = new JLabel("⚔ LOGIC VAULT GAME");
        title.setForeground(GOLD);
        title.setFont(new Font("Serif", Font.BOLD, 28));

        JPanel playerInfo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playerInfo.setBackground(PANEL);

        nameLabel = createLabel("");
        levelLabel = createLabel("");

        playerInfo.add(nameLabel);
        playerInfo.add(Box.createHorizontalStrut(20));
        playerInfo.add(levelLabel);

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(playerInfo, BorderLayout.EAST);

        gamePanel.add(topPanel, BorderLayout.NORTH);
    }

    // ========================================================
    // LEFT PANEL
    // ========================================================

    private void createLeftPanel() {

        JPanel leftContainer = new JPanel();
        leftContainer.setBackground(BACKGROUND);
        leftContainer.setLayout(new BoxLayout(leftContainer, BoxLayout.Y_AXIS));

        statsPanel = createStatsPanel();
        inventoryPanel = createInventoryPanel();

        leftContainer.add(statsPanel);
        leftContainer.add(Box.createVerticalStrut(10));
        leftContainer.add(inventoryPanel);

        gamePanel.add(leftContainer, BorderLayout.WEST);
    }

    // ========================================================
    // STATS PANEL
    // ========================================================

    private JPanel createStatsPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(PURPLE),
                        "PLAYER STATS",
                        0,
                        0,
                        new Font("SansSerif", Font.BOLD, 14),
                        TEXT
                )
        );

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250, 300));

        hpLabel = createLabel("");
        staminaLabel = createLabel("");
        manaLabel = createLabel("");
        xpLabel = createLabel("");
        goldLabel = createLabel("");

        hpBar = createProgressBar(RED);
        staminaBar = createProgressBar(GREEN);
        manaBar = createProgressBar(BLUE);
        xpBar = createProgressBar(GOLD);

        panel.add(hpLabel);
        panel.add(hpBar);

        panel.add(staminaLabel);
        panel.add(staminaBar);

        panel.add(manaLabel);
        panel.add(manaBar);

        panel.add(xpLabel);
        panel.add(xpBar);

        panel.add(Box.createVerticalStrut(10));
        panel.add(goldLabel);

        return panel;
    }

    // ========================================================
    // INVENTORY PANEL
    // ========================================================

    private JPanel createInventoryPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(GOLD),
                        "INVENTORY",
                        0,
                        0,
                        new Font("SansSerif", Font.BOLD, 14),
                        TEXT
                )
        );

        panel.setLayout(new GridLayout(5, 1, 3, 3));
        panel.setPreferredSize(new Dimension(250, 190));

        weaponLabel = createLabel("");
        keyLabel = createLabel("");
        gemLabel = createLabel("");
        shieldLabel = createLabel("");
        curseLabel = createLabel("");

        panel.add(weaponLabel);
        panel.add(keyLabel);
        panel.add(gemLabel);
        panel.add(shieldLabel);
        panel.add(curseLabel);

        return panel;
    }

    // ========================================================
    // CENTER PANEL
    // ========================================================

    private void createCenterPanel() {

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.setBackground(BACKGROUND);

        enemyPanel = createEnemyPanel();

        battleLog = new JTextArea();
        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);
        battleLog.setFont(new Font("Monospaced", Font.PLAIN, 14));
        battleLog.setForeground(TEXT);
        battleLog.setBackground(new Color(15, 15, 25));
        battleLog.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(battleLog);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(BLUE),
                        "ADVENTURE LOG",
                        0,
                        0,
                        new Font("SansSerif", Font.BOLD, 14),
                        TEXT
                )
        );

        center.add(enemyPanel, BorderLayout.NORTH);
        center.add(scrollPane, BorderLayout.CENTER);

        gamePanel.add(center, BorderLayout.CENTER);
    }

    // ========================================================
    // ENEMY PANEL
    // ========================================================

    private JPanel createEnemyPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(PANEL_LIGHT);
        panel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(RED),
                        "ENEMY",
                        0,
                        0,
                        new Font("SansSerif", Font.BOLD, 14),
                        TEXT
                )
        );

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        enemyNameLabel = createLabel("");
        enemyNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        enemyHpLabel = createLabel("");
        enemyHpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        enemyHpBar = createProgressBar(RED);
        enemyHpBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        enemyHpBar.setMaximumSize(new Dimension(450, 25));

        panel.add(enemyNameLabel);
        panel.add(enemyHpLabel);
        panel.add(enemyHpBar);

        return panel;
    }

    // ========================================================
    // BOTTOM ACTION PANEL
    // ========================================================

    private void createBottomPanel() {

        actionPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        actionPanel.setBackground(BACKGROUND);
        actionPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        JButton attackButton = createButton("⚔ ATTACK", RED);
        JButton magicButton = createButton("✦ MAGIC", PURPLE);
        JButton exploreButton = createButton("🗺 EXPLORE", BLUE);
        JButton restButton = createButton("❤ REST", GREEN);

        JButton inventoryButton = createButton("🎒 ITEMS", GOLD);
        JButton logicButton = createButton("∧ ∨ ¬ LOGIC", PURPLE);
        JButton questButton = createButton("★ QUEST", GOLD);
        JButton quitButton = createButton("EXIT", Color.GRAY);

        attackButton.addActionListener(this::attack);
        magicButton.addActionListener(this::magicAttack);
        exploreButton.addActionListener(this::explore);
        restButton.addActionListener(this::rest);

        inventoryButton.addActionListener(e -> showInventory());
        logicButton.addActionListener(e -> showLogicExplanation());
        questButton.addActionListener(e -> showQuest());
        quitButton.addActionListener(e -> exitGame());

        actionPanel.add(attackButton);
        actionPanel.add(magicButton);
        actionPanel.add(exploreButton);
        actionPanel.add(restButton);

        actionPanel.add(inventoryButton);
        actionPanel.add(logicButton);
        actionPanel.add(questButton);
        actionPanel.add(quitButton);

        gamePanel.add(actionPanel, BorderLayout.SOUTH);
    }

    // ========================================================
    // ATTACK
    // ========================================================

    private void attack(ActionEvent event) {

        /*
         * ====================================================
         * AND OPERATOR
         * ====================================================
         *
         * The player can attack only when:
         *
         * 1. The player has a weapon
         * AND
         * 2. The player has enough stamina
         *
         * Both conditions must be TRUE.
         */

        boolean enoughStamina = stamina >= 10;

        if (hasWeapon && enoughStamina) {

            int damage = random.nextInt(16) + 10;

            enemyHp -= damage;
            stamina -= 10;

            log("⚔ " + playerName + " attacks!");
            log("Damage dealt: " + damage);
            log("AND condition: hasWeapon && enoughStamina = TRUE");

            enemyTurn();

            checkEnemyStatus();

        } else {

            log("❌ Attack failed.");

            if (!hasWeapon) {
                log("You need a weapon.");
            }

            if (!enoughStamina) {
                log("You need at least 10 stamina.");
            }

            log("AND condition: hasWeapon && enoughStamina = FALSE");
        }

        updateUI();
    }

    // ========================================================
    // MAGIC ATTACK
    // ========================================================

    private void magicAttack(ActionEvent event) {

        /*
         * ====================================================
         * OR OPERATOR
         * ====================================================
         *
         * Magic can be used if:
         *
         * 1. The player has a Magic Gem
         * OR
         * 2. The player has at least 20 mana.
         *
         * Only one of the conditions needs to be TRUE.
         */

        boolean hasEnoughMana = mana >= 20;

        if (hasMagicGem || hasEnoughMana) {

            int damage = random.nextInt(21) + 15;

            enemyHp -= damage;

            if (!hasMagicGem) {
                mana -= 20;
            }

            log("✦ " + playerName + " casts a magic attack!");
            log("Magic damage: " + damage);
            log("OR condition: hasMagicGem || hasEnoughMana = TRUE");

            enemyTurn();

            checkEnemyStatus();

        } else {

            log("❌ Magic attack failed.");
            log("You need a Magic Gem OR at least 20 mana.");
            log("OR condition: hasMagicGem || hasEnoughMana = FALSE");
        }

        updateUI();
    }

    // ========================================================
    // EXPLORE
    // ========================================================

    private void explore(ActionEvent event) {

        int result = random.nextInt(5);

        if (result == 0) {

            hasWeapon = true;

            log("🗡 You explored the forest.");
            log("You found an ancient sword!");
            log("Inventory updated: Weapon acquired.");

        } else if (result == 1) {

            hasKey = true;

            log("🔑 You discovered a mysterious key.");
            log("The key may open the Logic Vault.");

        } else if (result == 2) {

            hasMagicGem = true;

            log("💎 You found a glowing Magic Gem.");
            log("The gem can be used for magic attacks.");

        } else if (result == 3) {

            hasShield = true;

            log("🛡 You found a Guardian Shield.");
            log("Your defense has increased.");

        } else {

            int foundGold = random.nextInt(31) + 10;

            gold += foundGold;

            log("💰 You found " + foundGold + " gold!");
        }

        updateUI();
    }

    // ========================================================
    // REST
    // ========================================================

    private void rest(ActionEvent event) {

        /*
         * NOT OPERATOR EXAMPLE
         *
         * !cursed means:
         *
         * "The player is NOT cursed."
         *
         * If cursed is FALSE:
         * !cursed becomes TRUE.
         */

        if (!cursed) {

            hp = Math.min(maxHp, hp + 20);
            stamina = Math.min(maxStamina, stamina + 20);
            mana = Math.min(maxMana, mana + 15);

            log("❤ You rest beside the campfire.");
            log("NOT condition: !cursed = TRUE");
            log("HP, stamina, and mana restored.");

        } else {

            log("❌ You cannot rest safely.");
            log("NOT condition: !cursed = FALSE");
            log("You are currently cursed!");

        }

        updateUI();
    }

    // ========================================================
    // ENEMY TURN
    // ========================================================

    private void enemyTurn() {

        if (enemyHp <= 0) {
            return;
        }

        int damage = random.nextInt(enemyAttack) + 5;

        if (hasShield) {
            damage = Math.max(1, damage - 5);
            log("🛡 Your shield reduced the damage.");
        }

        hp -= damage;

        log("👹 " + enemyName + " attacks you!");
        log("You received " + damage + " damage.");

        if (hp <= 0) {

            hp = 0;

            log("💀 You have been defeated.");
            showDefeatScreen();
        }
    }

    // ========================================================
    // CHECK ENEMY STATUS
    // ========================================================

    private void checkEnemyStatus() {

        if (enemyHp <= 0) {

            enemyHp = 0;
            enemyDefeated = true;

            int reward = 100;

            gold += reward;
            xp += 75;

            log("");
            log("🏆 " + enemyName + " has been defeated!");
            log("You received " + reward + " gold.");
            log("You received 75 XP.");

            checkLevelUp();

            /*
             * NOT OPERATOR
             *
             * !enemyDefeated means
             * "enemy is NOT defeated."
             *
             * After defeating the enemy:
             * !enemyDefeated = FALSE
             */

            log("NOT condition: !enemyDefeated = FALSE");

            unlockVault();
        }
    }

    // ========================================================
    // UNLOCK VAULT
    // ========================================================

    private void unlockVault() {

        /*
         * ====================================================
         * OR OPERATOR
         * ====================================================
         *
         * The player can open the vault if:
         *
         * hasKey OR hasMagicGem
         *
         * Either item is enough.
         */

        if (hasKey || hasMagicGem) {

            log("");
            log("🔓 You discovered the Logic Vault!");
            log("OR condition: hasKey || hasMagicGem = TRUE");

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "You defeated the Shadow Goblin.\n"
                    + "You have a key or magic gem.\n\n"
                    + "Open the Logic Vault?",
                    "Logic Vault",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                log("✨ The Logic Vault opens!");

                int treasure = random.nextInt(151) + 100;

                gold += treasure;
                xp += 100;

                log("💰 You found " + treasure + " gold!");
                log("⭐ You gained 100 XP!");

                checkLevelUp();

                JOptionPane.showMessageDialog(
                        this,
                        "Congratulations, " + playerName + "!\n\n"
                        + "You opened the Logic Vault!\n"
                        + "Treasure: " + treasure + " gold",
                        "VICTORY!",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                log("You decided not to open the vault yet.");
            }

        } else {

            log("🔒 The vault remains locked.");
            log("You need a key OR a Magic Gem.");
            log("OR condition: hasKey || hasMagicGem = FALSE");
        }
    }

    // ========================================================
    // LEVEL UP
    // ========================================================

    private void checkLevelUp() {

        while (xp >= xpNeeded) {

            xp -= xpNeeded;
            level++;

            xpNeeded += 50;

            maxHp += 15;
            maxStamina += 5;
            maxMana += 5;

            hp = maxHp;
            stamina = maxStamina;
            mana = maxMana;

            log("");
            log("⭐ LEVEL UP!");
            log("You are now Level " + level + "!");
            log("Maximum HP increased.");
            log("Maximum stamina increased.");
            log("Maximum mana increased.");
        }
    }

    // ========================================================
    // SHOW INVENTORY
    // ========================================================

    private void showInventory() {

        String message =
                "INVENTORY\n\n"
                + "🗡 Weapon: " + yesNo(hasWeapon) + "\n"
                + "🔑 Key: " + yesNo(hasKey) + "\n"
                + "💎 Magic Gem: " + yesNo(hasMagicGem) + "\n"
                + "🛡 Shield: " + yesNo(hasShield) + "\n"
                + "☠ Cursed: " + yesNo(cursed) + "\n\n"
                + "Gold: " + gold;

        JOptionPane.showMessageDialog(
                this,
                message,
                "Inventory",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ========================================================
    // QUEST
    // ========================================================

    private void showQuest() {

        String questStatus;

        if (!enemyDefeated) {

            questStatus =
                    "QUEST: DEFEAT THE SHADOW GOBLIN\n\n"
                    + "Enemy HP: " + enemyHp + "/" + enemyMaxHp + "\n"
                    + "Reward: 100 Gold + 75 XP";

        } else {

            questStatus =
                    "QUEST COMPLETE!\n\n"
                    + "The Shadow Goblin has been defeated.\n"
                    + "Find and open the Logic Vault.";
        }

        JOptionPane.showMessageDialog(
                this,
                questStatus,
                "Quest",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ========================================================
    // LOGIC EXPLANATION
    // ========================================================

    private void showLogicExplanation() {

        String explanation =
                "LOGICAL OPERATORS USED IN THIS RPG\n\n"

                + "1. AND (&&)\n"
                + "Used in ATTACK.\n\n"
                + "hasWeapon && enoughStamina\n\n"
                + "Both conditions must be TRUE.\n"
                + "Example:\n"
                + "Weapon = TRUE\n"
                + "Enough Stamina = TRUE\n"
                + "Result = TRUE\n\n"

                + "----------------------------------\n\n"

                + "2. OR (||)\n"
                + "Used in MAGIC and the VAULT.\n\n"
                + "hasMagicGem || hasEnoughMana\n\n"
                + "At least one condition must be TRUE.\n"
                + "Example:\n"
                + "Magic Gem = TRUE\n"
                + "Mana = FALSE\n"
                + "Result = TRUE\n\n"

                + "----------------------------------\n\n"

                + "3. NOT (!)\n"
                + "Used when RESTING.\n\n"
                + "!cursed\n\n"
                + "This means the player is NOT cursed.\n\n"
                + "Cursed = FALSE\n"
                + "!cursed = TRUE\n\n"

                + "The game uses logical conditions\n"
                + "to decide whether actions are allowed.";

        JTextArea area = new JTextArea(explanation);

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setBackground(BACKGROUND);
        area.setForeground(TEXT);
        area.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 500));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Logical Operations",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ========================================================
    // DEFEAT SCREEN
    // ========================================================

    private void showDefeatScreen() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "You were defeated!\n\n"
                + "Would you like to restart the adventure?",
                "GAME OVER",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {

            startGame();

        } else {

            System.exit(0);
        }
    }

    // ========================================================
    // EXIT GAME
    // ========================================================

    private void exitGame() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Game",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // ========================================================
    // UPDATE UI
    // ========================================================

    private void updateUI() {

        if (nameLabel == null) {
            return;
        }

        nameLabel.setText("Hero: " + playerName);
        levelLabel.setText("Level " + level);

        hpLabel.setText("❤ HP: " + hp + " / " + maxHp);
        staminaLabel.setText("⚡ Stamina: " + stamina + " / " + maxStamina);
        manaLabel.setText("✦ Mana: " + mana + " / " + maxMana);
        xpLabel.setText("★ XP: " + xp + " / " + xpNeeded);
        goldLabel.setText("💰 Gold: " + gold);

        hpBar.setMaximum(maxHp);
        hpBar.setValue(hp);

        staminaBar.setMaximum(maxStamina);
        staminaBar.setValue(stamina);

        manaBar.setMaximum(maxMana);
        manaBar.setValue(mana);

        xpBar.setMaximum(xpNeeded);
        xpBar.setValue(xp);

        weaponLabel.setText("🗡 Weapon: " + yesNo(hasWeapon));
        keyLabel.setText("🔑 Key: " + yesNo(hasKey));
        gemLabel.setText("💎 Magic Gem: " + yesNo(hasMagicGem));
        shieldLabel.setText("🛡 Shield: " + yesNo(hasShield));
        curseLabel.setText("☠ Cursed: " + yesNo(cursed));

        enemyNameLabel.setText("👹 " + enemyName);

        enemyHpLabel.setText(
                "Enemy HP: " + enemyHp + " / " + enemyMaxHp
        );

        enemyHpBar.setMaximum(enemyMaxHp);
        enemyHpBar.setValue(enemyHp);

        if (enemyDefeated) {

            enemyNameLabel.setText("🏆 " + enemyName + " - DEFEATED");
            enemyNameLabel.setForeground(GREEN);

        } else {

            enemyNameLabel.setForeground(TEXT);
        }

        if (hp <= maxHp / 4) {
            hpLabel.setForeground(RED);
        } else {
            hpLabel.setForeground(TEXT);
        }

        if (cursed) {
            curseLabel.setForeground(RED);
        } else {
            curseLabel.setForeground(GREEN);
        }
    }

    // ========================================================
    // UTILITY: YES / NO
    // ========================================================

    private String yesNo(boolean value) {

        if (value) {
            return "YES";
        }

        return "NO";
    }

    // ========================================================
    // UTILITY: CREATE LABEL
    // ========================================================

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);
        label.setForeground(TEXT);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setBorder(new EmptyBorder(5, 8, 5, 8));

        return label;
    }

    // ========================================================
    // UTILITY: CREATE PROGRESS BAR
    // ========================================================

    private JProgressBar createProgressBar(Color color) {

        JProgressBar bar = new JProgressBar();

        bar.setStringPainted(true);
        bar.setForeground(color);
        bar.setBackground(new Color(25, 25, 35));
        bar.setPreferredSize(new Dimension(210, 22));
        bar.setMaximumSize(new Dimension(220, 22));

        return bar;
    }

    // ========================================================
    // UTILITY: CREATE BUTTON
    // ========================================================

    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                color.brighter(),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                8,
                                8,
                                8
                        )
                )
        );

        return button;
    }

    // ========================================================
    // LOGGING SYSTEM
    // ========================================================

    private void log(String message) {

        if (battleLog != null) {

            battleLog.append(message + "\n");
            battleLog.setCaretPosition(
                    battleLog.getDocument().getLength()
            );
        }
    }

    // ========================================================
    // MAIN METHOD
    // ========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LogicVaultGame game = new LogicVaultGame();

            game.setVisible(true);
        });
    }
}

