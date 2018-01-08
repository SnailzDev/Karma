/*
This file is part of Karma.

Karma is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Karma is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Karma.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.snailz.karma.data;

import net.snailz.karma.user.KarmaUser;

import java.util.UUID;

public interface DataStorage {

    void sterilize(KarmaUser user);

    KarmaUser deSterilize(UUID uuid);

    void addNewKarmaUser(KarmaUser karmaUser);

    String getStorageMethod();
}
