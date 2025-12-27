package repook.yellowstoned.client.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.api.PlayerVariableInterface;

import java.util.List;

public class GuideBookScreen extends Screen {
    PlayerEntity player;
    ItemStack item;
    private int page = 0;
    private final int MAX_PAGES = 4; // however many pages you have
    private static final Identifier GUIDE_BOOK_PAGE_0 = new Identifier(Yellowstoned.MOD_ID,"textures/gui/guide_book_page0.png");
    private static final Identifier GUIDE_BOOK_PAGE_1 = new Identifier(Yellowstoned.MOD_ID,"textures/gui/guide_book_page1.png");
    private static final Identifier GUIDE_BOOK_PAGE_2 = new Identifier(Yellowstoned.MOD_ID,"textures/gui/guide_book_page2.png");
    private static final Identifier GUIDE_BOOK_PAGE_3 = new Identifier(Yellowstoned.MOD_ID,"textures/gui/guide_book_page3.png");
    private static final Identifier GUIDE_BOOK_PAGE_4 = new Identifier(Yellowstoned.MOD_ID,"textures/gui/guide_book_page4.png");

    private static final Identifier BUTTON_TEX_BEAVER = new Identifier(Yellowstoned.MOD_ID,"textures/gui/beaver_button.png");
    private static final Identifier BUTTON_TEX_MOOSE = new Identifier(Yellowstoned.MOD_ID,"textures/gui/moose_button.png");
    private static final Identifier BUTTON_TEX_REINDEER = new Identifier(Yellowstoned.MOD_ID,"textures/gui/reindeer_button.png");

    private final String[] PAGE_TEXT = new String[] {
            "", // page 0 (cover, no text)
            "The Beaver is a small, passive mammal which spawns on solid land near rivers. These large rodents are equipped with large front teeth which can pierce through solid logs. If these creatures are fed some Wood Soup, they will chop down the nearest logs around it for 30 seconds. The creature's fondness for logs can also be used to breed 2 of them.",
            "The Moose is a large, neutral mammal which can be found in the old growth spruce and taiga biomes. They attack when provoked, causing large knockback and moderate damage. The moose has a small chance to drop one of it’s antlers, which can be used as part of a display or for a new helmet.",
            "Reindeer travel in large packs across the snowy plains, looking for any bits of food they can find.",
            // ... up to page 9
    };
    public GuideBookScreen(PlayerEntity player, ItemStack item) {
        super(Text.literal("Guide Book"));
        this.player = player;
        this.item = item;
    }

    @Override
    protected void init() {//buttons
        rebuildPageButtons();
    }


    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        switch (page) {
            case 0 -> drawPages(GUIDE_BOOK_PAGE_0,GUIDE_BOOK_PAGE_1,ctx);
            case 1 -> drawPages(GUIDE_BOOK_PAGE_2,GUIDE_BOOK_PAGE_4,ctx);
            case 2 -> drawPages(GUIDE_BOOK_PAGE_3,GUIDE_BOOK_PAGE_4,ctx);
            case 3 -> drawPages(GUIDE_BOOK_PAGE_3,GUIDE_BOOK_PAGE_4,ctx);
        }

        // Draw the text for this page
        drawPageText(ctx);

        super.render(ctx, mouseX, mouseY, delta);
    }
    private void drawPageText(DrawContext ctx) {
//        ctx.getMatrices().push();
//        ctx.getMatrices().scale(0.85f, 0.85f, 1.0f);
//        // wrapped paragraph here
//
//        List<OrderedText> wrapped = this.textRenderer.wrapLines(Text.literal(PAGE_TEXT[page]), 120);
//
//        int middleX = (this.width - 185) / 2;
//        int rightPageX = middleX + 72;
//
//// add padding inside the page
//        int x = rightPageX + 85;
//        int y = 20;
//
//
//        for (OrderedText line : wrapped) {
//            ctx.drawText(this.textRenderer, line, x, y, 0x000000, false);
//            y += 10;
//        }
//        ctx.getMatrices().pop();
        float scale = 0.85f;

        // --- Calculate page position in NORMAL screen space ---
        int middleX = (this.width - 185) / 2;
        int rightPageX = middleX + 72;

        int pagePaddingX = 32;
        int pagePaddingY = 20;

        int baseX = rightPageX + pagePaddingX;
        int baseY = pagePaddingY;

        ctx.getMatrices().push();

        // Move origin to text anchor point
        ctx.getMatrices().translate(baseX, baseY, 0);

        // Scale relative to that anchor
        ctx.getMatrices().scale(scale, scale, 1.0f);

        // Now draw at (0, 0) in scaled space
        List<OrderedText> wrapped =
                this.textRenderer.wrapLines(Text.literal(PAGE_TEXT[page]), (int)(110 / scale));

        int y = 0;
        for (OrderedText line : wrapped) {
            ctx.drawText(this.textRenderer, line, 0, y, 0x000000, false);
            y += 10;
        }

        ctx.getMatrices().pop();
    }

    private void drawPages(Identifier texture1,Identifier texture2, DrawContext ctx){
        int MiddleX = (this.width - 185) / 2;
        int LeftX = MiddleX - 72;
        int RightX = MiddleX + 72;

        ctx.drawTexture(texture1,LeftX,2,0,0,192,192);
        ctx.drawTexture(texture2,RightX,2,0,0,192,192);

    }

    private void rebuildPageButtons() {
        this.clearChildren();  // removes old buttons/widgets

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Page navigation buttons (always present)
//        this.addDrawableChild(ButtonWidget.builder(Text.literal("<"), (b) -> changePage(-1))
//                .dimensions(centerX - 60, centerY + 70, 20, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("<"), (b) -> resetToPage0())
                .dimensions(centerX - 60, centerY + 70, 20, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal(">"), (b) -> changePage(1))
                .dimensions(centerX + 40, centerY + 70, 20, 20).build());

        // Page-specific buttons
        switch (page) {
            case 0 -> addPage0Buttons(centerX, centerY);
            //case 1 -> addPage1Buttons(centerX, centerY);
            //case 2 -> addPage2Buttons(centerX, centerY);
        }
    }

    private void addPage0Buttons(int centerX, int centerY) {
        PlayerVariableInterface variableInterface = (PlayerVariableInterface) player;
        int value = variableInterface.yellowstoned$getMyValue();
        if(value == 1 || !item.hasNbt()) {

            this.addDrawableChild(new TexturedButtonWidget(
                    centerX + 20, centerY - 100,                // position on screen
                    20, 20,              // width, height of button
                    0, 0,                // u, v (location inside the texture)
                    20,                  // vOffset when hovered
                    BUTTON_TEX_BEAVER,          // texture
                    20, 20,              // full texture width & height
                    (btn) -> {
                        System.out.println("Clicked!");
                        page = 1;
                        rebuildPageButtons();
                    }
            ));
        }
        this.addDrawableChild(new TexturedButtonWidget(
                centerX + 40, centerY - 100,// position on screen
                20, 20,              // width, height of button
                0, 0,                // u, v (location inside the texture)
                20,                  // vOffset when hovered
                BUTTON_TEX_MOOSE,          // texture
                20, 20,              // full texture width & height
                (btn) -> {
                    System.out.println("Clicked!");
                    page = 2;
                    rebuildPageButtons();
                }
        ));
        this.addDrawableChild(new TexturedButtonWidget(
                centerX + 60, centerY - 100,// position on screen
                20, 20,              // width, height of button
                0, 0,                // u, v (location inside the texture)
                20,                  // vOffset when hovered
                BUTTON_TEX_REINDEER,          // texture
                20, 20,              // full texture width & height
                (btn) -> {
                    System.out.println("Clicked!");
                    page = 3;
                    rebuildPageButtons();
                }
        ));
    }
    private boolean isUnlocked(ItemStack book, String id) {
        if (!book.hasNbt()) return false;

        NbtCompound ys = book.getNbt().getCompound("yellowstoned");
        if (ys == null) return false;

        NbtCompound unlocked = ys.getCompound("unlocked");
        if (unlocked == null) return false;

        return unlocked.getBoolean(id);
    }
    String getOwner(ItemStack stack, PlayerEntity entity){
        if (!stack.hasNbt())return null;
        if (!stack.getNbt().contains("yellowstoned.owner")) return null;
        NbtCompound nbt = stack.getNbt();
        return nbt.getString("yellowstoned.owner");
    }
    boolean isOwner(ItemStack stack, PlayerEntity player) {
        if (!stack.hasNbt())return false;
        System.out.println("has nbt");
        NbtCompound nbt = stack.getNbt();
        if (!stack.getNbt().contains("yellowstoned.owner")) return false;

        return nbt.getString("yellowstoned.owner").equals(player.getGameProfile().getId().toString());
    }
    private void addPage1Buttons(int centerX, int centerY) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Laser"), (b) -> {
            System.out.println("Button on Page 1 clicked!");
        }).dimensions(centerX - 40, centerY - 10, 80, 20).build());
    }

    private void changePage(int direction) {
        int newPage = page + direction;
        if (newPage < 0 || newPage >= MAX_PAGES) return;
        page = newPage;
        rebuildPageButtons();
    }

    private void resetToPage0() {
        page = 0;
        rebuildPageButtons();
    }


    @Override
    public boolean shouldPause() {
        return true;
    }
}
