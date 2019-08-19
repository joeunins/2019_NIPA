package com.codacy.analysis.core.utils

import java.util.concurrent.ForkJoinPool

import scala.collection.parallel.ForkJoinTaskSupport
import scala.collection.parallel.immutable.ParSet

object SetOps {

  def mapInParallel[A, B](set: Set[A], nrParallel: Option[Int] = Option.empty[Int])(f: A => B): Seq[B] = {
    val setPar: ParSet[A] = set.par
    setPar.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(nrParallel.getOrElse(2)))
    setPar.map(f)(collection.breakOut)
  }

}
