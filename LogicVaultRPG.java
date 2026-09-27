import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LogicVaultRPG extends JFrame {

    // =========================================================
    // PLAYER
    // =========================================================

    private String playerName = "Hero";

    private int level = 1;
    private final int maxLevel = 10;

    private int playerHP = 100;
    private int maxHP = 100;

    private int stamina = 50;
    private int maxStamina = 50;

    private int mana = 40;
    private int maxMana = 40;

    private int attackPower = 15;
    private int magicPower = 25;
    private int defense = 0;

    private int xp = 0;
    private int xpNeeded = 100;
    private int gold = 0;

    // =========================================================
    // INVENTORY
    // =========================================================

    private int healthPotions = 2;
    private int staminaPotions = 1;
    private int manaPotions = 1;
    private int powerPotions = 0;
    private int defensePotions = 0;

    private boolean hasWeapon = true;
    private boolean hasMagicGem = false;
    private boolean cursed = false;

    // =========================================================
    // ENEMY
    // =========================================================

    private String enemyName = "Shadow Goblin";

    private int enemyHP = 100;
    private int maxEnemyHP = 100;
    private int enemyDamage = 10;

    // =========================================================
    // ENEMY NAMES
    // Names now match their bodies/appearance.
    // =========================================================

    private final String[] enemyNames = {
        "Goblin",
        "Dire Wolf",
        "Stone Golem",
        "Blood Knight",
        "Flame Beast",
        "Frost Wraith",
        "Storm Orc",
        "Abyss Demon",
        "Crimson Dragon",
        "Void Overlord"
    };

    private final int[] enemyHPValues = {
        100,
        150,
        220,
        300,
        400,
        520,
        650,
        800,
        1000,
        1500
    };

    private final int[] enemyDamageValues = {
        10,
        14,
        18,
        23,
        28,
        34,
        40,
        48,
        58,
        70
    };

    private final int[] xpRewards = {
        100,
        140,
        180,
        230,
        300,
        380,
        470,
        580,
        700,
        1000
    };

    private final int[] goldRewards = {
        50,
        80,
        120,
        170,
        230,
        300,
        400,
        500,
        700,
        1500
    };

    // =========================================================
    // POSITIONS
    // =========================================================

    private int playerX = 170;
    private int playerY = 285;

    private int enemyX = 650;
    private int enemyY = 285;

    // =========================================================
    // AIMING
    // =========================================================

    private boolean playerAimingRight = true;
    private boolean enemyAimingLeft = true;

    // =========================================================
    // ANIMATION
    // =========================================================

    private boolean moving = false;
    private boolean attacking = false;
    private boolean casting = false;

    private boolean enemyMoving = false;
    private boolean enemyAttacking = false;
    private boolean enemyCasting = false;

    private int attackFrame = 0;
    private int magicFrame = 0;

    private int enemyAttackFrame = 0;
    private int enemyMagicFrame = 0;

    // =========================================================
    // GAME
    // =========================================================

    private boolean enemyDefeated = false;
    private boolean gameStarted = false;

    private String message =
        "Welcome to the Logic Vault!";

    private Random random =
        new Random();

    private GamePanel gamePanel;

    // =========================================================
    // PROJECTILE DATA
    // =========================================================

    private boolean playerProjectile = false;
    private int playerProjectileX = 0;
    private int playerProjectileY = 0;

    private boolean enemyProjectile = false;
    private int enemyProjectileX = 0;
    private int enemyProjectileY = 0;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LogicVaultRPG() {

        setTitle("Logic Vault RPG - AND OR NOT");

        setSize(1000, 700);

        setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        showStartScreen();
    }

    // =========================================================
    // START SCREEN
    // =========================================================

    private void showStartScreen() {

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(
            new Color(15, 15, 35)
        );

        JLabel title =
            new JLabel("LOGIC VAULT");

        title.setForeground(Color.WHITE);

        title.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                52
            )
        );

        title.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        title.setBounds(
            200,
            90,
            600,
            70
        );

        JLabel subtitle =
            new JLabel(
                "THE 10 LEVEL AND • OR • NOT RPG"
            );

        subtitle.setForeground(
            Color.LIGHT_GRAY
        );

        subtitle.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                20
            )
        );

        subtitle.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        subtitle.setBounds(
            200,
            165,
            600,
            40
        );

        JButton startButton =
            new JButton(
                "START ADVENTURE"
            );

        startButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                20
            )
        );

        startButton.setBounds(
            350,
            260,
            300,
            60
        );

        JButton logicButton =
            new JButton(
                "HOW LOGIC WORKS"
            );

        logicButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        logicButton.setBounds(
            375,
            340,
            250,
            50
        );

        JLabel info =
            new JLabel(
                "<html><center>" +
                "Defeat 10 increasingly powerful enemies.<br>" +
                "Move, aim, attack, collect loot and level up!" +
                "</center></html>"
            );

        info.setForeground(
            Color.WHITE
        );

        info.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                16
            )
        );

        info.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        info.setBounds(
            250,
            430,
            500,
            80
        );

        startButton.addActionListener(
            e -> startGame()
        );

        logicButton.addActionListener(
            e -> showLogicExplanation()
        );

        panel.add(title);
        panel.add(subtitle);
        panel.add(startButton);
        panel.add(logicButton);
        panel.add(info);

        setContentPane(panel);

        revalidate();
        repaint();
    }

    // =========================================================
    // START GAME
    // =========================================================

    private void startGame() {

        String name =
            JOptionPane.showInputDialog(
                this,
                "Enter your hero name:",
                "Create Hero",
                JOptionPane.QUESTION_MESSAGE
            );

        if (name == null ||
            name.trim().isEmpty()) {

            playerName = "Hero";

        } else {

            playerName =
                name.trim();
        }

        level = 1;

        maxHP = 100;
        playerHP = maxHP;

        maxStamina = 50;
        stamina = maxStamina;

        maxMana = 40;
        mana = maxMana;

        attackPower = 15;
        magicPower = 25;
        defense = 0;

        xp = 0;
        xpNeeded = 100;
        gold = 0;

        healthPotions = 2;
        staminaPotions = 1;
        manaPotions = 1;
        powerPotions = 0;
        defensePotions = 0;

        hasWeapon = true;
        hasMagicGem = false;
        cursed = false;

        gameStarted = true;

        loadLevel();

        showGameScreen();
    }

    // =========================================================
    // LOAD LEVEL
    // =========================================================

    private void loadLevel() {

        enemyName =
            enemyNames[level - 1];

        maxEnemyHP =
            enemyHPValues[level - 1];

        enemyHP =
            maxEnemyHP;

        enemyDamage =
            enemyDamageValues[level - 1];

        enemyDefeated = false;

        playerX = 170;
        enemyX = 700;

        playerAimingRight = true;
        enemyAimingLeft = true;

        playerProjectile = false;
        enemyProjectile = false;

        message =
            "Level " + level +
            ": " + enemyName +
            " has appeared!";
    }

    // =========================================================
    // GAME SCREEN
    // =========================================================

    private void showGameScreen() {

        gamePanel =
            new GamePanel();

        setContentPane(
            gamePanel
        );

        revalidate();
        repaint();
    }

    // =========================================================
    // PLAYER MOVE FORWARD
    // =========================================================

    private void moveForward() {

        if (moving ||
            attacking ||
            casting ||
            enemyDefeated) {

            return;
        }

        if (playerX >= 560) {

            message =
                "You cannot move any closer.";

            repaint();

            return;
        }

        moving = true;

        Timer timer =
            new Timer(20, null);

        timer.addActionListener(
            e -> {

                playerX += 5;

                if (playerX >= 560) {

                    playerX = 560;

                    moving = false;

                    timer.stop();

                    message =
                        "You moved forward.";

                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // PLAYER MOVE BACKWARD
    // =========================================================

    private void moveBackward() {

        if (moving ||
            attacking ||
            casting ||
            enemyDefeated) {

            return;
        }

        if (playerX <= 80) {

            message =
                "You cannot move farther back.";

            repaint();

            return;
        }

        moving = true;

        Timer timer =
            new Timer(20, null);

        timer.addActionListener(
            e -> {

                playerX -= 5;

                if (playerX <= 80) {

                    playerX = 80;

                    moving = false;

                    timer.stop();

                    message =
                        "You moved backward.";
                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // AIM LEFT
    // =========================================================

    private void aimLeft() {

        playerAimingRight = false;

        message =
            "You are aiming LEFT.";

        repaint();
    }

    // =========================================================
    // AIM RIGHT
    // =========================================================

    private void aimRight() {

        playerAimingRight = true;

        message =
            "You are aiming RIGHT.";

        repaint();
    }

    // =========================================================
    // PLAYER SWORD ATTACK
    // =========================================================

    private void attack() {

        if (attacking ||
            casting ||
            enemyDefeated) {

            return;
        }

        boolean enoughStamina =
            stamina >= 10;

        // AND OPERATOR
        boolean canAttack =
            hasWeapon &&
            enoughStamina;

        if (!canAttack) {

            message =
                "AND = FALSE: You need a weapon AND enough stamina.";

            repaint();

            return;
        }

        if (!playerAimingRight) {

            message =
                "Aim RIGHT toward the enemy first.";

            repaint();

            return;
        }

        if (Math.abs(
            enemyX - playerX
        ) > 180) {

            message =
                "The enemy is too far away for your sword!";

            repaint();

            return;
        }

        stamina -= 10;

        attacking = true;

        attackFrame = 0;

        final int damage =
            attackPower +
            random.nextInt(11);

        message =
            "AND = TRUE: Weapon ✓ AND Stamina ✓";

        Timer timer =
            new Timer(40, null);

        timer.addActionListener(
            e -> {

                attackFrame++;

                if (attackFrame >= 12) {

                    enemyHP -= damage;

                    if (enemyHP < 0) {
                        enemyHP = 0;
                    }

                    attacking = false;

                    timer.stop();

                    message =
                        "Sword hit " +
                        enemyName +
                        " for " +
                        damage +
                        " damage!";

                    if (enemyHP <= 0) {

                        defeatEnemy();

                    } else {

                        enemyTurn();
                    }
                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // PLAYER MAGIC
    // =========================================================

    private void magicAttack() {

        if (attacking ||
            casting ||
            enemyDefeated) {

            return;
        }

        boolean enoughMana =
            mana >= 20;

        // OR OPERATOR
        boolean canCast =
            hasMagicGem ||
            enoughMana;

        if (!canCast) {

            message =
                "OR = FALSE: Need a Magic Gem OR 20 Mana.";

            repaint();

            return;
        }

        if (!playerAimingRight) {

            message =
                "Aim RIGHT toward the enemy first.";

            repaint();

            return;
        }

        if (Math.abs(
            enemyX - playerX
        ) > 500) {

            message =
                "The enemy is too far away.";

            repaint();

            return;
        }

        if (!hasMagicGem) {

            mana -= 20;
        }

        casting = true;

        magicFrame = 0;

        playerProjectileX =
            playerX + 50;

        playerProjectileY =
            playerY;

        playerProjectile = true;

        final int damage =
            magicPower +
            random.nextInt(16);

        message =
            "OR = TRUE: Magic Gem OR enough Mana!";

        Timer timer =
            new Timer(30, null);

        timer.addActionListener(
            e -> {

                playerProjectileX += 15;

                magicFrame++;

                if (playerProjectileX >= enemyX) {

                    enemyHP -= damage;

                    if (enemyHP < 0) {
                        enemyHP = 0;
                    }

                    playerProjectile = false;

                    casting = false;

                    timer.stop();

                    message =
                        "Magic projectile hit for " +
                        damage +
                        " damage!";

                    if (enemyHP <= 0) {

                        defeatEnemy();

                    } else {

                        enemyTurn();
                    }
                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // REST
    // =========================================================

    private void rest() {

        if (enemyDefeated) {
            return;
        }

        // NOT OPERATOR
        if (!cursed) {

            playerHP += 20;
            stamina += 20;
            mana += 15;

            if (playerHP > maxHP) {
                playerHP = maxHP;
            }

            if (stamina > maxStamina) {
                stamina = maxStamina;
            }

            if (mana > maxMana) {
                mana = maxMana;
            }

            message =
                "NOT = TRUE: You are NOT cursed → REST succeeds.";

        } else {

            message =
                "NOT = FALSE: You are cursed → REST fails.";
        }

        repaint();
    }

    // =========================================================
    // ENEMY TURN
    // =========================================================

    private void enemyTurn() {

        if (enemyDefeated ||
            enemyMoving ||
            enemyAttacking ||
            enemyCasting) {

            return;
        }

        Timer delay =
            new Timer(
                700,
                e -> {

                    ((Timer) e.getSource()).stop();

                    enemyAI();
                }
            );

        delay.setRepeats(false);

        delay.start();
    }

    // =========================================================
    // ENEMY AI
    // =========================================================

    private void enemyAI() {

        if (enemyDefeated) {
            return;
        }

        int distance =
            Math.abs(
                enemyX - playerX
            );

        enemyAimingLeft =
            enemyX > playerX;

        // Enemy approaches if far away
        if (distance > 230) {

            enemyMoveTowardPlayer();

            return;
        }

        // Enemy retreats sometimes
        if (distance < 110 &&
            random.nextInt(4) == 0) {

            enemyMoveBackward();

            return;
        }

        // Enemy attacks
        enemyAttack();
    }

    // =========================================================
    // ENEMY MOVE TOWARD PLAYER
    // =========================================================

    private void enemyMoveTowardPlayer() {

        if (enemyMoving) {
            return;
        }

        enemyMoving = true;

        enemyAimingLeft =
            enemyX > playerX;

        Timer timer =
            new Timer(20, null);

        timer.addActionListener(
            e -> {

                if (enemyX > playerX + 160) {

                    enemyX -= 5;

                } else if (
                    enemyX <
                    playerX - 160
                ) {

                    enemyX += 5;

                } else {

                    enemyMoving = false;

                    timer.stop();

                    message =
                        enemyName +
                        " moves toward you!";

                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // ENEMY MOVE BACKWARD
    // =========================================================

    private void enemyMoveBackward() {

        if (enemyMoving) {
            return;
        }

        enemyMoving = true;

        Timer timer =
            new Timer(20, null);

        timer.addActionListener(
            e -> {

                enemyX += 5;

                if (enemyX >= 850) {

                    enemyX = 850;

                    enemyMoving = false;

                    timer.stop();

                    message =
                        enemyName +
                        " retreats backward!";
                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // ENEMY ATTACK
    // =========================================================

    private void enemyAttack() {

        if (enemyDefeated ||
            enemyAttacking) {

            return;
        }

        enemyAttacking = true;

        enemyAttackFrame = 0;

        enemyAimingLeft =
            enemyX > playerX;

        message =
            enemyName +
            " is preparing an attack!";

        Timer timer =
            new Timer(40, null);

        timer.addActionListener(
            e -> {

                enemyAttackFrame++;

                if (enemyAttackFrame >= 15) {

                    int distance =
                        Math.abs(
                            enemyX - playerX
                        );

                    // Close-range attack
                    if (distance <= 220) {

                        int damage =
                            enemyDamage +
                            random.nextInt(8);

                        damage -= defense;

                        if (damage < 1) {
                            damage = 1;
                        }

                        playerHP -= damage;

                        if (playerHP < 0) {
                            playerHP = 0;
                        }

                        message =
                            enemyName +
                            " struck you for " +
                            damage +
                            " damage!";

                    } else {

                        // Ranged projectile
                        enemyProjectileX =
                            enemyX - 40;

                        enemyProjectileY =
                            enemyY;

                        enemyProjectile = true;

                        enemyAttacking = false;

                        timer.stop();

                        enemyProjectileAttack();

                        repaint();

                        return;
                    }

                    enemyAttacking = false;

                    timer.stop();

                    if (playerHP <= 0) {

                        gameOver();
                    }

                    repaint();
                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // ENEMY PROJECTILE
    // =========================================================

    private void enemyProjectileAttack() {

        enemyCasting = true;

        enemyMagicFrame = 0;

        Timer timer =
            new Timer(30, null);

        timer.addActionListener(
            e -> {

                enemyProjectileX -= 15;

                enemyMagicFrame++;

                if (enemyProjectileX <= playerX + 40) {

                    int damage =
                        enemyDamage +
                        random.nextInt(8);

                    damage -= defense;

                    if (damage < 1) {
                        damage = 1;
                    }

                    playerHP -= damage;

                    if (playerHP < 0) {
                        playerHP = 0;
                    }

                    enemyProjectile = false;

                    enemyCasting = false;

                    timer.stop();

                    message =
                        enemyName +
                        " launched an attack for " +
                        damage +
                        " damage!";

                    if (playerHP <= 0) {

                        gameOver();
                    }
                }

                repaint();
            }
        );

        timer.start();
    }

    // =========================================================
    // HEALTH POTION
    // =========================================================

    private void useHealthPotion() {

        if (healthPotions <= 0) {

            message =
                "You have no Health Potions!";

            repaint();

            return;
        }

        if (playerHP >= maxHP) {

            message =
                "Your HP is already full!";

            repaint();

            return;
        }

        healthPotions--;

        playerHP += 50;

        if (playerHP > maxHP) {
            playerHP = maxHP;
        }

        message =
            "Health Potion consumed!";

        repaint();
    }

    // =========================================================
    // STAMINA POTION
    // =========================================================

    private void useStaminaPotion() {

        if (staminaPotions <= 0) {

            message =
                "You have no Stamina Potions!";

            repaint();

            return;
        }

        staminaPotions--;

        stamina += 35;

        if (stamina > maxStamina) {
            stamina = maxStamina;
        }

        message =
            "Stamina Potion consumed!";

        repaint();
    }

    // =========================================================
    // MANA POTION
    // =========================================================

    private void useManaPotion() {

        if (manaPotions <= 0) {

            message =
                "You have no Mana Potions!";

            repaint();

            return;
        }

        manaPotions--;

        mana += 30;

        if (mana > maxMana) {
            mana = maxMana;
        }

        message =
            "Mana Potion consumed!";

        repaint();
    }

    // =========================================================
    // POWER POTION
    // =========================================================

    private void usePowerPotion() {

        if (powerPotions <= 0) {

            message =
                "You have no Power Potions!";

            repaint();

            return;
        }

        powerPotions--;

        attackPower += 8;

        magicPower += 10;

        message =
            "POWER POTION! Attack and Magic increased!";

        repaint();
    }

    // =========================================================
    // DEFENSE POTION
    // =========================================================

    private void useDefensePotion() {

        if (defensePotions <= 0) {

            message =
                "You have no Defense Potions!";

            repaint();

            return;
        }

        defensePotions--;

        defense += 3;

        message =
            "DEFENSE POTION! Defense increased!";

        repaint();
    }

    // =========================================================
    // ITEMS
    // =========================================================

    private void showItemsMenu() {

        String[] options = {
            "Health Potion",
            "Stamina Potion",
            "Mana Potion",
            "Power Potion",
            "Defense Potion",
            "Cancel"
        };

        int choice =
            JOptionPane.showOptionDialog(
                this,
                "Choose an item to consume:",
                "INVENTORY",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

        switch (choice) {

            case 0:
                useHealthPotion();
                break;

            case 1:
                useStaminaPotion();
                break;

            case 2:
                useManaPotion();
                break;

            case 3:
                usePowerPotion();
                break;

            case 4:
                useDefensePotion();
                break;

            default:
                break;
        }
    }

    // =========================================================
    // MAGIC GEM
    // =========================================================

    private void getMagicGem() {

        if (hasMagicGem) {

            message =
                "You already have the Magic Gem!";

        } else {

            hasMagicGem = true;

            message =
                "MAGIC GEM obtained!";
        }

        repaint();
    }

    // =========================================================
    // DEFEAT ENEMY
    // =========================================================

    private void defeatEnemy() {

        enemyDefeated = true;

        enemyProjectile = false;

        int earnedXP =
            xpRewards[level - 1];

        int earnedGold =
            goldRewards[level - 1];

        xp += earnedXP;

        gold += earnedGold;

        message =
            "VICTORY! " +
            enemyName +
            " defeated! +" +
            earnedXP +
            " XP  +" +
            earnedGold +
            " Gold!";

        repaint();

        Timer timer =
            new Timer(
                1000,
                e -> {

                    ((Timer) e.getSource()).stop();

                    giveLoot();
                }
            );

        timer.setRepeats(false);

        timer.start();
    }

    // =========================================================
    // LOOT
    // =========================================================

    private void giveLoot() {

        int loot =
            random.nextInt(5);

        if (loot == 0) {

            healthPotions++;

            message =
                "LOOT: Health Potion obtained!";

        } else if (loot == 1) {

            staminaPotions++;

            message =
                "LOOT: Stamina Potion obtained!";

        } else if (loot == 2) {

            manaPotions++;

            message =
                "LOOT: Mana Potion obtained!";

        } else if (loot == 3) {

            powerPotions++;

            message =
                "LOOT: Power Potion obtained!";

        } else {

            defensePotions++;

            message =
                "LOOT: Defense Potion obtained!";
        }

        repaint();

        Timer timer =
            new Timer(
                900,
                e -> {

                    ((Timer) e.getSource()).stop();

                    showLootWindow();
                }
            );

        timer.setRepeats(false);

        timer.start();
    }

    // =========================================================
    // LOOT WINDOW
    // =========================================================

    private void showLootWindow() {

        String lootText =
            "LEVEL " +
            level +
            " VICTORY!\n\n" +

            "Enemy: " +
            enemyName +
            "\n\n" +

            "XP: +" +
            xpRewards[level - 1] +
            "\n" +

            "Gold: +" +
            goldRewards[level - 1] +
            "\n\n" +

            "INVENTORY\n" +

            "Health Potions: " +
            healthPotions +
            "\n" +

            "Stamina Potions: " +
            staminaPotions +
            "\n" +

            "Mana Potions: " +
            manaPotions +
            "\n" +

            "Power Potions: " +
            powerPotions +
            "\n" +

            "Defense Potions: " +
            defensePotions;

        JOptionPane.showMessageDialog(
            this,
            lootText,
            "LOOT FOUND!",
            JOptionPane.INFORMATION_MESSAGE
        );

        checkLevelUp();
    }

    // =========================================================
    // LEVEL UP
    // =========================================================

    private void checkLevelUp() {

        boolean leveledUp = false;

        while (
            xp >= xpNeeded &&
            level < maxLevel
        ) {

            xp -= xpNeeded;

            level++;

            xpNeeded += 50;

            maxHP += 20;
            playerHP = maxHP;

            maxStamina += 5;
            stamina = maxStamina;

            maxMana += 5;
            mana = maxMana;

            attackPower += 5;

            magicPower += 7;

            leveledUp = true;
        }

        if (leveledUp) {

            message =
                "LEVEL UP! You are now Level " +
                level + "!";

            repaint();

            JOptionPane.showMessageDialog(
                this,
                "LEVEL UP!\n\n" +
                "You are now Level " +
                level +
                "!\n\n" +

                "Max HP increased!\n" +
                "Max Stamina increased!\n" +
                "Max Mana increased!\n" +
                "Attack increased!\n" +
                "Magic increased!",
                "LEVEL UP",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        if (level >= maxLevel) {

            showFinalVictory();

        } else {

            askNextLevel();
        }
    }

    // =========================================================
    // NEXT LEVEL
    // =========================================================

    private void askNextLevel() {

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Prepare for Level " +
                level +
                "?\n\n" +

                "Enemy: " +
                enemyNames[level - 1] +
                "\n" +

                "Enemy HP: " +
                enemyHPValues[level - 1] +
                "\n" +

                "Enemy Damage: " +
                enemyDamageValues[level - 1],

                "NEXT LEVEL",

                JOptionPane.YES_NO_OPTION
            );

        if (answer ==
            JOptionPane.YES_OPTION) {

            loadLevel();

            showGameScreen();

        } else {

            message =
                "You are preparing for the next battle.";

            showGameScreen();
        }
    }

    // =========================================================
    // GAME OVER
    // =========================================================

    private void gameOver() {

        JOptionPane.showMessageDialog(
            this,
            "GAME OVER!\n\n" +
            enemyName +
            " defeated you.\n\n" +
            "You reached Level " +
            level +
            ".",
            "DEFEATED",
            JOptionPane.ERROR_MESSAGE
        );

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Restart the adventure?",
                "RESTART",
                JOptionPane.YES_NO_OPTION
            );

        if (answer ==
            JOptionPane.YES_OPTION) {

            startGame();

        } else {

            System.exit(0);
        }
    }

    // =========================================================
    // FINAL VICTORY
    // =========================================================

    private void showFinalVictory() {

        JOptionPane.showMessageDialog(
            this,
            "CONGRATULATIONS, " +
            playerName +
            "!\n\n" +

            "You defeated the\n" +
            "VOID OVERLORD!\n\n" +

            "ALL 10 LEVELS COMPLETED!\n\n" +

            "Final Attack: " +
            attackPower +
            "\n" +

            "Final Magic: " +
            magicPower +
            "\n" +

            "Final Defense: " +
            defense +
            "\n" +

            "Gold: " +
            gold +
            "\n\n" +

            "You have mastered the Logic Vault!",
            "FINAL VICTORY",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // LOGIC EXPLANATION
    // =========================================================

    private void showLogicExplanation() {

        JTextArea text =
            new JTextArea();

        text.setEditable(false);

        text.setFont(
            new Font(
                "Monospaced",
                Font.PLAIN,
                14
            )
        );

        text.setText(
            "LOGIC VAULT - LOGICAL OPERATIONS\n\n" +

            "AND (&&)\n" +
            "Both conditions must be TRUE.\n\n" +

            "Game example:\n" +
            "hasWeapon && enoughStamina\n\n" +

            "The player can attack only when:\n" +
            "Weapon = TRUE\n" +
            "AND\n" +
            "Enough Stamina = TRUE\n\n" +

            "----------------------------------------\n\n" +

            "OR (||)\n" +
            "At least ONE condition must be TRUE.\n\n" +

            "Game example:\n" +
            "hasMagicGem || enoughMana\n\n" +

            "The player can use magic when:\n" +
            "Magic Gem = TRUE\n" +
            "OR\n" +
            "Enough Mana = TRUE\n\n" +

            "----------------------------------------\n\n" +

            "NOT (!)\n" +
            "NOT reverses a Boolean value.\n\n" +

            "Game example:\n" +
            "!cursed\n\n" +

            "If cursed = FALSE,\n" +
            "!cursed = TRUE.\n\n" +

            "Therefore, the player can rest.\n\n" +

            "----------------------------------------\n\n" +

            "COMBAT\n" +
            "The player can move forward/backward.\n" +
            "The enemy can move forward/backward.\n" +
            "Both characters have an aiming direction.\n" +
            "Attacks are shown visually on the battlefield.\n" +
            "Enemies become stronger every level."
        );

        JScrollPane scroll =
            new JScrollPane(text);

        scroll.setPreferredSize(
            new Dimension(
                650,
                500
            )
        );

        JOptionPane.showMessageDialog(
            this,
            scroll,
            "HOW LOGIC WORKS",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // GAME PANEL
    // =========================================================

    private class GamePanel
        extends JPanel {

        private JButton forwardButton;
        private JButton backwardButton;

        private JButton aimLeftButton;
        private JButton aimRightButton;

        private JButton attackButton;
        private JButton magicButton;

        private JButton restButton;
        private JButton itemButton;

        private JButton gemButton;
        private JButton logicButton;

        public GamePanel() {

            setLayout(null);

            setBackground(
                new Color(25, 25, 45)
            );

            createButtons();

            positionButtons();
        }

        // =====================================================
        // BUTTONS
        // =====================================================

        private void createButtons() {

            forwardButton =
                new JButton("FORWARD");

            backwardButton =
                new JButton("BACK");

            aimLeftButton =
                new JButton("AIM LEFT");

            aimRightButton =
                new JButton("AIM RIGHT");

            attackButton =
                new JButton("ATTACK");

            magicButton =
                new JButton("MAGIC");

            restButton =
                new JButton("REST");

            itemButton =
                new JButton("ITEMS");

            gemButton =
                new JButton("GET GEM");

            logicButton =
                new JButton("LOGIC");

            forwardButton.addActionListener(
                e -> moveForward()
            );

            backwardButton.addActionListener(
                e -> moveBackward()
            );

            aimLeftButton.addActionListener(
                e -> aimLeft()
            );

            aimRightButton.addActionListener(
                e -> aimRight()
            );

            attackButton.addActionListener(
                e -> attack()
            );

            magicButton.addActionListener(
                e -> magicAttack()
            );

            restButton.addActionListener(
                e -> rest()
            );

            itemButton.addActionListener(
                e -> showItemsMenu()
            );

            gemButton.addActionListener(
                e -> getMagicGem()
            );

            logicButton.addActionListener(
                e -> showLogicExplanation()
            );

            add(forwardButton);
            add(backwardButton);
            add(aimLeftButton);
            add(aimRightButton);

            add(attackButton);
            add(magicButton);
            add(restButton);
            add(itemButton);
            add(gemButton);
            add(logicButton);
        }

        // =====================================================
        // BUTTON POSITIONS
        // =====================================================

        private void positionButtons() {

            int y =
                getHeight() - 70;

            forwardButton.setBounds(
                15, y, 90, 42
            );

            backwardButton.setBounds(
                110, y, 80, 42
            );

            aimLeftButton.setBounds(
                195, y, 95, 42
            );

            aimRightButton.setBounds(
                295, y, 100, 42
            );

            attackButton.setBounds(
                400, y, 90, 42
            );

            magicButton.setBounds(
                495, y, 90, 42
            );

            restButton.setBounds(
                590, y, 75, 42
            );

            itemButton.setBounds(
                670, y, 75, 42
            );

            gemButton.setBounds(
                750, y, 90, 42
            );

            logicButton.setBounds(
                845, y, 90, 42
            );
        }

        @Override
        public void doLayout() {

            super.doLayout();

            positionButtons();
        }

        // =====================================================
        // PAINT
        // =====================================================

        @Override
        protected void paintComponent(
            Graphics g
        ) {

            super.paintComponent(g);

            Graphics2D g2 =
                (Graphics2D) g.create();

            drawBackground(g2);

            drawLevelInfo(g2);

            drawPlayer(g2);

            drawEnemy(g2);

            drawAimLines(g2);

            drawPlayerAttackEffect(g2);

            drawPlayerProjectile(g2);

            drawEnemyAttackEffect(g2);

            drawEnemyProjectile(g2);

            drawHUD(g2);

            drawMessage(g2);

            g2.dispose();
        }

        // =====================================================
        // BACKGROUND
        // =====================================================

        private void drawBackground(
            Graphics2D g2
        ) {

            int width =
                getWidth();

            int height =
                getHeight();

            g2.setColor(
                new Color(
                    20,
                    25,
                    55
                )
            );

            g2.fillRect(
                0,
                0,
                width,
                height
            );

            // Moon
            g2.setColor(
                new Color(
                    240,
                    240,
                    190
                )
            );

            g2.fillOval(
                760,
                55,
                75,
                75
            );

            // Stars
            g2.setColor(Color.WHITE);

            for (
                int i = 0;
                i < 35;
                i++
            ) {

                int x =
                    (i * 83)
                    % width;

                int y =
                    40 +
                    (i * 37)
                    % 200;

                g2.fillOval(
                    x,
                    y,
                    3,
                    3
                );
            }

            // Ground
            g2.setColor(
                new Color(
                    35,
                    75,
                    45
                )
            );

            g2.fillRect(
                0,
                390,
                width,
                height - 390
            );

            g2.setColor(
                new Color(
                    75,
                    130,
                    65
                )
            );

            g2.fillRect(
                0,
                390,
                width,
                5
            );
        }

        // =====================================================
        // LEVEL INFO
        // =====================================================

        private void drawLevelInfo(
            Graphics2D g2
        ) {

            g2.setColor(
                Color.WHITE
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    22
                )
            );

            g2.drawString(
                "LEVEL " +
                level +
                " / 10",
                30,
                40
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    18
                )
            );

            g2.drawString(
                "Enemy: " +
                enemyName,
                30,
                70
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.PLAIN,
                    14
                )
            );

            g2.drawString(
                "XP: " +
                xp +
                " / " +
                xpNeeded,
                30,
                95
            );

            g2.drawString(
                "Gold: " +
                gold,
                30,
                115
            );
        }

        // =====================================================
        // PLAYER
        // =====================================================

        private void drawPlayer(
            Graphics2D g2
        ) {

            int x =
                playerX;

            int y =
                playerY;

            // Shadow
            g2.setColor(
                new Color(
                    0,
                    0,
                    0,
                    100
                )
            );

            g2.fillOval(
                x - 25,
                y + 70,
                65,
                15
            );

            // Head
            g2.setColor(
                new Color(
                    245,
                    190,
                    140
                )
            );

            g2.fillOval(
                x,
                y - 45,
                35,
                35
            );

            // Hair
            g2.setColor(
                new Color(
                    55,
                    35,
                    25
                )
            );

            g2.fillArc(
                x,
                y - 50,
                35,
                35,
                0,
                180
            );

            // Body
            g2.setColor(
                new Color(
                    55,
                    90,
                    160
                )
            );

            g2.fillRect(
                x - 5,
                y - 10,
                45,
                55
            );

            // Belt
            g2.setColor(
                new Color(
                    100,
                    65,
                    30
                )
            );

            g2.fillRect(
                x - 5,
                y + 25,
                45,
                8
            );

            // Legs
            g2.setColor(
                new Color(
                    40,
                    40,
                    65
                )
            );

            g2.fillRect(
                x,
                y + 45,
                12,
                35
            );

            g2.fillRect(
                x + 25,
                y + 45,
                12,
                35
            );

            // Sword
            g2.setStroke(
                new BasicStroke(5)
            );

            g2.setColor(
                new Color(
                    220,
                    220,
                    230
                )
            );

            int swordEndX;

            int swordEndY;

            if (playerAimingRight) {

                swordEndX =
                    x + 55;

                swordEndY =
                    y + 5;

            } else {

                swordEndX =
                    x - 30;

                swordEndY =
                    y + 5;
            }

            if (attacking) {

                swordEndX =
                    playerAimingRight
                    ? x + 85
                    : x - 60;

                swordEndY =
                    y - 15;
            }

            g2.drawLine(
                x + 20,
                y + 10,
                swordEndX,
                swordEndY
            );

            // Sword handle
            g2.setColor(
                new Color(
                    160,
                    100,
                    30
                )
            );

            g2.setStroke(
                new BasicStroke(4)
            );

            g2.drawLine(
                x + 15,
                y + 15,
                x + 30,
                y + 25
            );

            // Aim indicator
            g2.setColor(
                Color.YELLOW
            );

            if (playerAimingRight) {

                g2.drawLine(
                    x + 45,
                    y - 20,
                    x + 85,
                    y - 20
                );

            } else {

                g2.drawLine(
                    x - 45,
                    y - 20,
                    x - 5,
                    y - 20
                );
            }

            // Name
            g2.setColor(
                Color.WHITE
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    14
                )
            );

            g2.drawString(
                playerName,
                x - 5,
                y - 60
            );
        }

        // =====================================================
        // ENEMY
        // =====================================================

        private void drawEnemy(
            Graphics2D g2
        ) {

            if (enemyDefeated) {
                return;
            }

            int x =
                enemyX;

            int y =
                enemyY;

            if (level == 10) {

                drawVoidOverlord(
                    g2,
                    x,
                    y
                );

            } else if (level == 9) {

                drawCrimsonDragon(
                    g2,
                    x,
                    y
                );

            } else if (level == 8) {

                drawAbyssDemon(
                    g2,
                    x,
                    y
                );

            } else if (level == 7) {

                drawStormOrc(
                    g2,
                    x,
                    y
                );

            } else if (level == 6) {

                drawFrostWraith(
                    g2,
                    x,
                    y
                );

            } else if (level == 5) {

                drawFlameBeast(
                    g2,
                    x,
                    y
                );

            } else if (level == 4) {

                drawBloodKnight(
                    g2,
                    x,
                    y
                );

            } else if (level == 3) {

                drawStoneGolem(
                    g2,
                    x,
                    y
                );

            } else if (level == 2) {

                drawDireWolf(
                    g2,
                    x,
                    y
                );

            } else {

                drawGoblin(
                    g2,
                    x,
                    y
                );
            }

            // Enemy aim indicator
            g2.setColor(
                Color.ORANGE
            );

            g2.setStroke(
                new BasicStroke(3)
            );

            g2.drawLine(
                x - 50,
                y - 20,
                x - 10,
                y - 20
            );

            // Enemy name
            g2.setColor(
                Color.WHITE
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    14
                )
            );

            g2.drawString(
                enemyName,
                x - 15,
                y - 75
            );
        }

        // =====================================================
        // GOBLIN
        // =====================================================

        private void drawGoblin(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    90,
                    150,
                    80
                )
            );

            g2.fillOval(
                x - 5,
                y - 45,
                45,
                40
            );

            g2.setColor(
                new Color(
                    70,
                    120,
                    60
                )
            );

            g2.fillRect(
                x - 10,
                y - 5,
                55,
                65
            );

            g2.setColor(Color.RED);

            g2.fillOval(
                x + 5,
                y - 30,
                8,
                8
            );

            g2.fillOval(
                x + 27,
                y - 30,
                8,
                8
            );

            g2.setColor(
                new Color(
                    50,
                    60,
                    50
                )
            );

            g2.fillRect(
                x,
                y + 55,
                15,
                30
            );

            g2.fillRect(
                x + 28,
                y + 55,
                15,
                30
            );
        }

        // =====================================================
        // DIRE WOLF
        // =====================================================

        private void drawDireWolf(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    75,
                    80,
                    90
                )
            );

            g2.fillOval(
                x - 25,
                y - 20,
                100,
                55
            );

            g2.fillOval(
                x + 45,
                y - 45,
                55,
                50
            );

            Polygon ear =
                new Polygon();

            ear.addPoint(
                x + 55,
                y - 35
            );

            ear.addPoint(
                x + 60,
                y - 70
            );

            ear.addPoint(
                x + 75,
                y - 40
            );

            g2.fillPolygon(
                ear
            );

            g2.setColor(Color.RED);

            g2.fillOval(
                x + 75,
                y - 25,
                8,
                8
            );

            g2.setColor(
                new Color(
                    55,
                    55,
                    65
                )
            );

            g2.fillRect(
                x,
                y + 20,
                15,
                45
            );

            g2.fillRect(
                x + 50,
                y + 20,
                15,
                45
            );
        }

        // =====================================================
        // STONE GOLEM
        // =====================================================

        private void drawStoneGolem(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    110,
                    110,
                    120
                )
            );

            g2.fillRect(
                x - 25,
                y - 55,
                80,
                120
            );

            g2.fillOval(
                x - 20,
                y - 75,
                70,
                60
            );

            g2.setColor(
                new Color(
                    70,
                    70,
                    75
                )
            );

            g2.fillRect(
                x - 45,
                y - 5,
                25,
                70
            );

            g2.fillRect(
                x + 55,
                y - 5,
                25,
                70
            );

            g2.setColor(
                Color.ORANGE
            );

            g2.fillOval(
                x - 5,
                y - 50,
                12,
                12
            );

            g2.fillOval(
                x + 30,
                y - 50,
                12,
                12
            );
        }

        // =====================================================
        // BLOOD KNIGHT
        // =====================================================

        private void drawBloodKnight(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    90,
                    20,
                    25
                )
            );

            g2.fillRect(
                x - 15,
                y - 10,
                60,
                80
            );

            g2.setColor(
                new Color(
                    100,
                    100,
                    110
                )
            );

            g2.fillOval(
                x - 5,
                y - 65,
                45,
                50
            );

            g2.setColor(
                Color.RED
            );

            g2.fillRect(
                x - 10,
                y - 45,
                55,
                8
            );

            g2.setColor(
                new Color(
                    170,
                    170,
                    180
                )
            );

            g2.fillRect(
                x + 50,
                y - 5,
                8,
                90
            );
        }

        // =====================================================
        // FLAME BEAST
        // =====================================================

        private void drawFlameBeast(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    180,
                    60,
                    20
                )
            );

            g2.fillOval(
                x - 25,
                y - 65,
                85,
                110
            );

            g2.setColor(
                new Color(
                    240,
                    110,
                    20
                )
            );

            g2.fillOval(
                x - 10,
                y - 45,
                55,
                50
            );

            g2.setColor(
                Color.YELLOW
            );

            g2.fillOval(
                x,
                y - 25,
                10,
                10
            );

            g2.fillOval(
                x + 30,
                y - 25,
                10,
                10
            );

            Polygon fire =
                new Polygon();

            fire.addPoint(
                x + 60,
                y + 20
            );

            fire.addPoint(
                x + 110,
                y - 10
            );

            fire.addPoint(
                x + 75,
                y + 40
            );

            g2.fillPolygon(
                fire
            );
        }

        // =====================================================
        // FROST WRAITH
        // =====================================================

        private void drawFrostWraith(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    100,
                    200,
                    230,
                    190
                )
            );

            g2.fillOval(
                x - 25,
                y - 70,
                75,
                100
            );

            Polygon body =
                new Polygon();

            body.addPoint(
                x - 20,
                y
            );

            body.addPoint(
                x + 55,
                y
            );

            body.addPoint(
                x + 35,
                y + 90
            );

            body.addPoint(
                x,
                y + 60
            );

            g2.fillPolygon(
                body
            );

            g2.setColor(Color.WHITE);

            g2.fillOval(
                x - 5,
                y - 40,
                12,
                12
            );

            g2.fillOval(
                x + 30,
                y - 40,
                12,
                12
            );
        }

        // =====================================================
        // STORM ORC
        // =====================================================

        private void drawStormOrc(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    70,
                    100,
                    120
                )
            );

            g2.fillOval(
                x - 20,
                y - 65,
                70,
                60
            );

            g2.fillRect(
                x - 25,
                y - 5,
                80,
                90
            );

            g2.setColor(
                Color.YELLOW
            );

            g2.fillOval(
                x - 5,
                y - 45,
                13,
                13
            );

            g2.fillOval(
                x + 30,
                y - 45,
                13,
                13
            );

            // Lightning weapon
            g2.setColor(
                new Color(
                    180,
                    220,
                    255
                )
            );

            g2.setStroke(
                new BasicStroke(6)
            );

            g2.drawLine(
                x - 50,
                y,
                x - 90,
                y + 40
            );
        }

        // =====================================================
        // ABYSS DEMON
        // =====================================================

        private void drawAbyssDemon(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    65,
                    10,
                    85
                )
            );

            g2.fillOval(
                x - 25,
                y - 80,
                80,
                70
            );

            g2.fillRect(
                x - 35,
                y - 10,
                100,
                100
            );

            g2.setColor(
                new Color(
                    210,
                    40,
                    60
                )
            );

            g2.fillOval(
                x - 5,
                y - 50,
                15,
                12
            );

            g2.fillOval(
                x + 35,
                y - 50,
                15,
                12
            );

            // Horns
            g2.setColor(
                Color.LIGHT_GRAY
            );

            Polygon horn1 =
                new Polygon();

            horn1.addPoint(
                x - 10,
                y - 55
            );

            horn1.addPoint(
                x - 50,
                y - 110
            );

            horn1.addPoint(
                x + 5,
                y - 75
            );

            g2.fillPolygon(
                horn1
            );

            Polygon horn2 =
                new Polygon();

            horn2.addPoint(
                x + 40,
                y - 55
            );

            horn2.addPoint(
                x + 80,
                y - 110
            );

            horn2.addPoint(
                x + 50,
                y - 75
            );

            g2.fillPolygon(
                horn2
            );
        }

        // =====================================================
        // CRIMSON DRAGON
        // =====================================================

        private void drawCrimsonDragon(
            Graphics2D g2,
            int x,
            int y
        ) {

            g2.setColor(
                new Color(
                    150,
                    30,
                    35
                )
            );

            g2.fillOval(
                x - 25,
                y - 70,
                90,
                100
            );

            // Wings
            Polygon wing1 =
                new Polygon();

            wing1.addPoint(
                x,
                y
            );

            wing1.addPoint(
                x - 90,
                y - 70
            );

            wing1.addPoint(
                x - 50,
                y + 45
            );

            g2.fillPolygon(
                wing1
            );

            Polygon wing2 =
                new Polygon();

            wing2.addPoint(
                x + 45,
                y
            );

            wing2.addPoint(
                x + 125,
                y - 70
            );

            wing2.addPoint(
                x + 85,
                y + 45
            );

            g2.fillPolygon(
                wing2
            );

            g2.setColor(
                Color.YELLOW
            );

            g2.fillOval(
                x,
                y - 45,
                12,
                12
            );

            g2.fillOval(
                x + 38,
                y - 45,
                12,
                12
            );
        }

        // =====================================================
        // VOID OVERLORD
        // =====================================================

        private void drawVoidOverlord(
            Graphics2D g2,
            int x,
            int y
        ) {

            // Aura
            g2.setColor(
                new Color(
                    150,
                    0,
                    200,
                    60
                )
            );

            g2.fillOval(
                x - 80,
                y - 120,
                200,
                230
            );

            // Body
            g2.setColor(
                new Color(
                    45,
                    5,
                    65
                )
            );

            g2.fillRect(
                x - 45,
                y - 10,
                120,
                120
            );

            // Head
            g2.setColor(
                new Color(
                    55,
                    10,
                    75
                )
            );

            g2.fillOval(
                x - 30,
                y - 90,
                90,
                80
            );

            // Eyes
            g2.setColor(
                new Color(
                    255,
                    30,
                    80
                )
            );

            g2.fillOval(
                x - 5,
                y - 55,
                18,
                14
            );

            g2.fillOval(
                x + 38,
                y - 55,
                18,
                14
            );

            // Energy core
            g2.setColor(
                new Color(
                    220,
                    50,
                    255
                )
            );

            g2.fillOval(
                x,
                y + 10,
                50,
                50
            );

            // Horns
            g2.setColor(
                new Color(
                    220,
                    220,
                    230
                )
            );

            Polygon horn1 =
                new Polygon();

            horn1.addPoint(
                x - 10,
                y - 65
            );

            horn1.addPoint(
                x - 55,
                y - 130
            );

            horn1.addPoint(
                x + 5,
                y - 90
            );

            g2.fillPolygon(
                horn1
            );

            Polygon horn2 =
                new Polygon();

            horn2.addPoint(
                x + 45,
                y - 65
            );

            horn2.addPoint(
                x + 90,
                y - 130
            );

            horn2.addPoint(
                x + 55,
                y - 90
            );

            g2.fillPolygon(
                horn2
            );
        }

        // =====================================================
        // AIM LINES
        // =====================================================

        private void drawAimLines(
            Graphics2D g2
        ) {

            // Player aiming line
            g2.setStroke(
                new BasicStroke(
                    2,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL,
                    0,
                    new float[]{8, 8},
                    0
                )
            );

            g2.setColor(
                new Color(
                    255,
                    255,
                    100,
                    150
                )
            );

            if (playerAimingRight) {

                g2.drawLine(
                    playerX + 45,
                    playerY - 20,
                    enemyX - 40,
                    enemyY - 20
                );

            } else {

                g2.drawLine(
                    playerX - 45,
                    playerY - 20,
                    50,
                    playerY - 20
                );
            }

            // Enemy aiming line
            g2.setColor(
                new Color(
                    255,
                    100,
                    100,
                    120
                )
            );

            g2.drawLine(
                enemyX - 45,
                enemyY - 20,
                playerX + 40,
                playerY - 20
            );
        }

        // =====================================================
        // PLAYER SWORD EFFECT
        // =====================================================

        private void drawPlayerAttackEffect(
            Graphics2D g2
        ) {

            if (!attacking) {
                return;
            }

            g2.setColor(
                new Color(
                    255,
                    230,
                    50
                )
            );

            g2.setStroke(
                new BasicStroke(8)
            );

            if (playerAimingRight) {

                g2.drawArc(
                    playerX + 30,
                    playerY - 60,
                    100,
                    100,
                    270,
                    120
                );

            } else {

                g2.drawArc(
                    playerX - 100,
                    playerY - 60,
                    100,
                    100,
                    90,
                    120
                );
            }
        }

        // =====================================================
        // PLAYER MAGIC PROJECTILE
        // =====================================================

        private void drawPlayerProjectile(
            Graphics2D g2
        ) {

            if (!playerProjectile) {
                return;
            }

            g2.setColor(
                new Color(
                    200,
                    70,
                    255
                )
            );

            g2.fillOval(
                playerProjectileX - 15,
                playerProjectileY - 15,
                35,
                35
            );

            g2.setColor(
                Color.WHITE
            );

            g2.fillOval(
                playerProjectileX - 5,
                playerProjectileY - 5,
                12,
                12
            );
        }

        // =====================================================
        // ENEMY ATTACK EFFECT
        // =====================================================

        private void drawEnemyAttackEffect(
            Graphics2D g2
        ) {

            if (!enemyAttacking) {
                return;
            }

            g2.setColor(
                new Color(
                    255,
                    70,
                    70
                )
            );

            g2.setStroke(
                new BasicStroke(8)
            );

            g2.drawArc(
                enemyX - 120,
                enemyY - 60,
                100,
                100,
                70,
                120
            );
        }

        // =====================================================
        // ENEMY PROJECTILE
        // =====================================================

        private void drawEnemyProjectile(
            Graphics2D g2
        ) {

            if (!enemyProjectile) {
                return;
            }

            g2.setColor(
                new Color(
                    255,
                    60,
                    60
                )
            );

            g2.fillOval(
                enemyProjectileX - 15,
                enemyProjectileY - 15,
                35,
                35
            );

            g2.setColor(
                Color.YELLOW
            );

            g2.fillOval(
                enemyProjectileX - 5,
                enemyProjectileY - 5,
                12,
                12
            );
        }

        // =====================================================
        // HUD
        // =====================================================

        private void drawHUD(
            Graphics2D g2
        ) {

            int x = 30;
            int y = 450;

            g2.setColor(
                Color.WHITE
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    15
                )
            );

            g2.drawString(
                playerName +
                " - Level " +
                level,
                x,
                y
            );

            drawBar(
                g2,
                x,
                y + 10,
                220,
                20,
                playerHP,
                maxHP,
                "HP"
            );

            drawBar(
                g2,
                x,
                y + 40,
                220,
                20,
                stamina,
                maxStamina,
                "STAMINA"
            );

            drawBar(
                g2,
                x,
                y + 70,
                220,
                20,
                mana,
                maxMana,
                "MANA"
            );

            g2.setColor(
                Color.WHITE
            );

            g2.drawString(
                "Attack: " +
                attackPower,
                300,
                y + 30
            );

            g2.drawString(
                "Magic: " +
                magicPower,
                300,
                y + 55
            );

            g2.drawString(
                "Defense: " +
                defense,
                300,
                y + 80
            );

            g2.drawString(
                "Aiming: " +
                (
                    playerAimingRight
                    ? "RIGHT →"
                    : "← LEFT"
                ),
                300,
                y + 105
            );

            g2.drawString(
                "Potions:",
                500,
                y + 30
            );

            g2.drawString(
                "HP " +
                healthPotions +
                " | STA " +
                staminaPotions +
                " | MANA " +
                manaPotions,
                500,
                y + 55
            );

            g2.drawString(
                "POWER " +
                powerPotions +
                " | DEF " +
                defensePotions,
                500,
                y + 80
            );

            if (!enemyDefeated) {

                drawBar(
                    g2,
                    700,
                    450,
                    230,
                    22,
                    enemyHP,
                    maxEnemyHP,
                    enemyName
                );

                g2.setColor(
                    Color.WHITE
                );

                g2.drawString(
                    "Enemy Aim: ←",
                    700,
                    500
                );
            }
        }

        // =====================================================
        // BARS
        // =====================================================

        private void drawBar(
            Graphics2D g2,
            int x,
            int y,
            int width,
            int height,
            int value,
            int max,
            String label
        ) {

            g2.setColor(
                new Color(
                    50,
                    50,
                    50
                )
            );

            g2.fillRect(
                x,
                y,
                width,
                height
            );

            int fillWidth =
                (int)
                (
                    (double) value /
                    max *
                    width
                );

            if (fillWidth < 0) {
                fillWidth = 0;
            }

            if (fillWidth > width) {
                fillWidth = width;
            }

            if (
                label.equals("HP") ||
                label.equals(enemyName)
            ) {

                g2.setColor(
                    new Color(
                        200,
                        50,
                        50
                    )
                );

            } else if (
                label.equals("STAMINA")
            ) {

                g2.setColor(
                    new Color(
                        230,
                        180,
                        40
                    )
                );

            } else {

                g2.setColor(
                    new Color(
                        80,
                        100,
                        220
                    )
                );
            }

            g2.fillRect(
                x,
                y,
                fillWidth,
                height
            );

            g2.setColor(
                Color.WHITE
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    12
                )
            );

            g2.drawString(
                label +
                " " +
                value +
                "/" +
                max,
                x + 5,
                y + 16
            );
        }

        // =====================================================
        // MESSAGE
        // =====================================================

        private void drawMessage(
            Graphics2D g2
        ) {

            int y =
                getHeight() - 115;

            g2.setColor(
                new Color(
                    0,
                    0,
                    0,
                    180
                )
            );

            g2.fillRoundRect(
                15,
                y,
                getWidth() - 30,
                35,
                10,
                10
            );

            g2.setColor(
                Color.WHITE
            );

            g2.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    14
                )
            );

            g2.drawString(
                message,
                30,
                y + 23
            );
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            () -> {

                LogicVaultRPG game =
                    new LogicVaultRPG();

                game.setVisible(true);
            }
        );
    }
}