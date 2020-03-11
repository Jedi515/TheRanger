package TheRanger.relics;

import TheRanger.util.TextureLoader;
import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

@AutoAdd.Ignore
public abstract class RangerRelic
    extends CustomRelic
{
    public RangerRelic(String id, RelicTier tier, LandingSound sfx) {
        super(id, createTexture(id), tier, sfx);
    }

    private static Texture createTexture(String ID)
    {
        if (!Gdx.files.internal(makeImagePath(ID)).file().exists())
        {
            return TextureLoader.getTexture(makeImagePath("beta_rock"));
        }
        return TextureLoader.getTexture(makeImagePath(ID));
    }

    private static String makeImagePath(String ID)
    {
        return "resources/theRanger/images/relics/" + ID + ".png";
    }
}
