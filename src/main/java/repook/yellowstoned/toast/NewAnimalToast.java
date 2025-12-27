package repook.yellowstoned.toast;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NewAnimalToast implements Toast {

    private final Text title;
    private final Text description;

    private final Identifier texture;
    private long startTime;
    private boolean justCreated = true;

    public NewAnimalToast(Text title, Text description, Identifier texture) {
        this.title = title;
        this.description = description;
        this.texture = texture;
    }

    @Override
    public Visibility draw(DrawContext ctx, ToastManager manager, long time) {

        if (justCreated) {
            this.startTime = time;
            justCreated = false;
        }

        // Background texture (vanilla toast background)
        Identifier TEXTURE = new Identifier("minecraft", "textures/gui/toasts.png");
        ctx.drawTexture(TEXTURE, 0, 0, 0, 0, this.getWidth(), this.getHeight());

        // Title text
        ctx.drawText(manager.getClient().textRenderer, title, 30, 7, 0xFFFFFF, false);

        // Description text
        ctx.drawText(manager.getClient().textRenderer, description, 30, 18, 0xAAAAAA, false);

        // Custom icon (optional)

        ctx.drawTexture(texture,8,8,0,0,20,20,20,20);
        // Toast duration (5000 = 5 seconds)
        return time - startTime < 5000L ? Visibility.SHOW : Visibility.HIDE;
    }



    @Override
    public int getWidth() { return 160; }

    @Override
    public int getHeight() { return 32; }
}

