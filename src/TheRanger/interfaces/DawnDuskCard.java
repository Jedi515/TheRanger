package TheRanger.interfaces;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface DawnDuskCard
{
    void dawn(AbstractPlayer p, AbstractMonster m);
    void dusk(AbstractPlayer p);
}
